import java.io.*;

public class main {

    public static void main(String[] args) throws IOException {


        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("������� ����:");
        String path = bf.readLine();
        getFiles(path);
    }












    public static int i = 0;
    public static void getFiles(String path) {


        File directory = new File(path);                 //����

        File[] fileList = directory.listFiles();        //��������� ����� �� ���� � ������
        if (fileList.length > 0) {
            i++;
        }
        int length = fileList.length;
        for (File f: fileList) {
            String str = "";
            for (int ch=0; ch<i; ch++) {
                str += "\t";
            }
            if (f.isFile()) {
                System.out.println(str+f.getName() + "\t" + f.length() + " ����.");
                if (f.equals(fileList[length-1])) {
                    i--;
                }
            }else {
                System.out.println(str+"Folder " +"\t" + f.getName());
                getFiles(f.toString());
                if (f.equals(fileList[length-1])) {
                    i--;
                }
            }
        }
    }


}
