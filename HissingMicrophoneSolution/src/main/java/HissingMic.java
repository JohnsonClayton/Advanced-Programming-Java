
/**
 *
 * @author clayt
 */
public class HissingMic {
    private static String input;
    private static String output;
    
    public HissingMic() {
        input = "";
    }
    
    public static void main(String[] args) throws Exception{
        int ch;
        while((ch = System.in.read()) != '\n') {
            input += Character.toString((char) ch);
        }
        solve();
        System.out.println(getOutput());
    }
    
    public static void solve() throws Exception {
        int counter = 0;
        char ch;
        boolean found = false;
        for(int i = 0; i < input.length(); i+=1) {
            ch = input.charAt(i);
            //System.out.println(ch);
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
            output = "hiss";
        else
            output = "no hiss";
    }
    
    public static String getOutput() {
        return output;
    }
    
    public void setInput(String _input) {
        input = _input;
    }
    
}
