import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Handler extends DefaultHandler {


    private HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private HashMap<Voter, Integer> voterCounts;// = new HashMap<>();

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");



    Lock lock;
    Condition condition;
    ExecutorService ex;
    int readNodesCount =0;
    boolean isScanning = true;
    volatile boolean docWasRead = false;

    void preparePartiallyReading () {

        ex = Executors.newFixedThreadPool(1);
        lock = new ReentrantLock();
        condition = lock.newCondition();

        ex.execute(() -> {
            for (;;) {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    reader.readLine();
                    lock.lock();
                    isScanning = true;
                    condition.signal();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
    }


    @Override
    public void endElement (String uri, String localName, String qName) {
        readNodesCount++;
        if (readNodesCount == 1000 || docWasRead) {

            printResult();
            if(!docWasRead) {
                lock.lock();
                try {
                    System.out.println("Locking. Enter to continue");
                    isScanning = false;
                    readNodesCount = 0;

                    while (!isScanning) {
                        condition.await();
                    }
                } catch (InterruptedException ignore) {
                } finally {
                    lock.unlock();
                }
            }
        }
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
    public void endDocument() {
        docWasRead = true;
    }

    HashMap getVoters () {
        return voterCounts;
    }
    void printResult () {
        for (Voter v: voterCounts.keySet()) {
            System.out.println(v.toString());
        }
    }
}
