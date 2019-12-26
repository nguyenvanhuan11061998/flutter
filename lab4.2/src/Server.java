import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        Registry registry = LocateRegistry.createRegistry(5000);
        System.out.println("sever ready");
        TinhToan tt = new TinhToan();
        registry.rebind("tinhtoan", tt);
    }
}
