import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterFaceDemo extends Remote {
    String Tong(String chuoi) throws RemoteException;
}
