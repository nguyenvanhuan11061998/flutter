import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class TinhToan extends UnicastRemoteObject implements InterFaceDemo {

    protected TinhToan() throws RemoteException {
        super();
    }


    @Override
    public String Tong(String chuoi) {
        String chuoiMoi = "";

        ArrayList<Integer> arrNum1 = new ArrayList<>();
        arrNum1 = arNum(chuoi);
        ArrayList<Integer> arrNum = new ArrayList<>();
        arrNum = danxen(arrNum1);

        for (int i = 0; i < arrNum.size(); i++) {
            chuoiMoi = chuoiMoi + arrNum.get(i) + ", ";
        }
        return chuoiMoi;
    }

    private ArrayList<Integer> arNum(String chuoi) {
        ArrayList<Integer> arrNum = new ArrayList<>();
        String chuoi1 = chuoi.trim();
        while (chuoi1.length() > 0) {
            int indexSpace = chuoi1.indexOf(" ");
            if (indexSpace == -1) {
                arrNum.add(Integer.parseInt(chuoi1));
                return arrNum;
            } else {
                String so = chuoi1.substring(0, indexSpace);
                arrNum.add(Integer.parseInt(so));
                chuoi1 = chuoi1.substring(indexSpace + 1, chuoi1.length());
            }
        }
        return arrNum;
    }

    private  ArrayList<Integer> danxen(ArrayList<Integer> arrNum){
        ArrayList<Integer> arrChan = new ArrayList<>();
        ArrayList<Integer> arrLe = new ArrayList<>();
         for (int i = 0; i < arrNum.size(); i++) {
            if (arrNum.get(i)%2==0){
                arrChan.add(arrNum.get(i));
            }else {
                arrLe.add(arrNum.get(i));
            }
        }

        ArrayList<Integer> newArr = new ArrayList<>();
         if (arrChan.size()>arrLe.size()){
             for (int i = 0; i < arrLe.size()*2; i++) {
                 if (i%2== 0){
                     newArr.add(arrChan.get(i/2));
                 }else {
                     newArr.add(arrLe.get(i/2));
             }}

             for (int i = arrLe.size(); i < arrChan.size(); i++) {
                 newArr.add(arrChan.get(i));
             }
         }else {
             for (int i = 0; i < arrChan.size()*2; i++) {
                 if (i%2== 0){
                     newArr.add(arrChan.get(i/2));
                 }else {
                     newArr.add(arrLe.get(i/2));
                 }}

             for (int i = arrChan.size(); i < arrLe.size(); i++) {
                 newArr.add(arrLe.get(i));
             }
         }
            return newArr;
        }
    }
