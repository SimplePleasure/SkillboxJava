import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Handler extends DefaultHandler {


    private HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private HashMap<Voter, Integer> voterCounts;// = new HashMap<>();

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");



    Lock lock;
    Condition continueReading;
    Condition sendingElements;
    int readNodesCount =0;
    volatile boolean isScaning = false;

    Handler() {
        lock = new ReentrantLock();
        continueReading = lock.newCondition();
        sendingElements = lock.newCondition();
    }

    HashMap next() {
        isScaning = true;
        lock.lock();
        try {
            continueReading.signal();
            while (isScaning) {
                sendingElements.await();
            }
        }catch(InterruptedException e) {
        }finally {
            lock.unlock();
        }
        return voterCounts;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (readNodesCount == 0) {
            voterCounts = new HashMap<>();
        }
        try {
            if (qName.equals("voter")) {
                String name = attributes.getValue("name");
                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                Voter voter = new Voter(name, birthDay);
                voterCounts.merge(voter, 1, Integer::sum);

            }
            if (qName.equals("visit")) {
                Integer station = Integer.parseInt(attributes.getValue("station"));
                Date time = visitDateFormat.parse(attributes.getValue("time"));
                WorkTime workTime = voteStationWorkTimes.get(station);
                if (workTime == null) {
                    workTime = new WorkTime();
                    voteStationWorkTimes.put(station, workTime);
                }
                workTime.addVisitTime(time.getTime());

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement (String uri, String localName, String qName) {
        readNodesCount++;
        if (readNodesCount == 1000) {
            isScaning = false;
            readNodesCount = 0;
            lock.lock();
            try {
                System.out.println("Enter to continue");
                sendingElements.signal();
                while (!isScaning) {
                    continueReading.await();
                }
            } catch (InterruptedException ignore) {
            } finally {
                lock.unlock();
            }
        }
    }

    @Override
    public void startDocument() {
        lock.lock();
        try {
            while (!isScaning) {
                continueReading.await();
            }
        } catch (InterruptedException e) {
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void endDocument() {
        lock.lock();
        try {
            sendingElements.signal();
        } finally {
            lock.unlock();
        }

    }
}
