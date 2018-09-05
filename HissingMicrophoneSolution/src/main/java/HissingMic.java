
/**
 *
 * @author clayt
 */
public class HissingMic {
    public static void main(String[] args) throws Exception{
        int ch;
        int counter = 0;
        boolean found = false;
        while((ch = System.in.read()) != '\n')
        {
            if(ch == 's' && counter == 1) {
                found = true;
                counter = 0;   
            }
            else if(ch == 's' && counter == 0)
                counter += 1;
            else
                counter = 0;
        }
        if(found)
            System.out.println("hiss");
        else
            System.out.println("no hiss");
    }
}
