
package loginsystem;


public class Main {
    
      public static void main(String[] args) {
          
        IDandpasswords idandPasswords = new IDandpasswords();
       
        
        LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());
        
    }
    
}
