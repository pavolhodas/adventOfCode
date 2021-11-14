import java.util.regex.Pattern;

public class test {
    public static void main(String args[]) {
        String a = "eyr:2020";
        String[] parts = a.split(":");
        //System.out.println(Integer.valueOf(a));
        if(parts[0].equals("eyr") && Integer.parseInt(parts[1]) >= 2020 && Integer.parseInt(parts[1]) <= 2030){
            System.out.println("true");
        }
        }
    }
