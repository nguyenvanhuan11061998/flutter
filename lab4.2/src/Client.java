import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {

        Registry registry = LocateRegistry.getRegistry("localhost",5000);

        Scanner sc = new Scanner(System.in);
            System.out.println("nhap chuoi so: ");
            String chuoi = sc.nextLine();
            InterFaceDemo interFaceDemo = (InterFaceDemo) registry.lookup("tinhtoan");
            System.out.println("Chuoi chan le dan xen: " + interFaceDemo.Tong(chuoi));
    }
}
