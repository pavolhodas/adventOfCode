import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {
    List<String> listOfInput = new ArrayList<>();
    public static void main(String args[]) {

        Main main = new Main();

        String fileName = "E:\\pavol\\appslab2021\\input.txt";

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(main.listOfInput::add);

        } catch (IOException e) {
            e.printStackTrace();
        }
        main.isValid();
    }
    private int valid;
    HashMap<String, String> passportData = new HashMap<>();

    public void isValid(){

        List<String> listOfElements;

        for(int i = 0;i<listOfInput.size();i++){
            listOfElements = Arrays.asList(listOfInput.get(i).split(" "));
            boolean validField = false;
            for(int a = 0; a<listOfElements.size();a++) {


                    String[] parts = listOfElements.get(a).split(":");

                    //byr

                    if(parts[0].equals("byr") && Integer.parseInt(parts[1]) >= 1920 && Integer.parseInt(parts[1]) <= 2002){
                        validField = true;
                    }

                    //iyr

                    else if(parts[0].equals("iyr") && Integer.parseInt(parts[1]) >= 2010 && Integer.parseInt(parts[1]) <= 2020){
                        validField = true;
                    }

                    //eyr

                    else if(parts[0].equals("eyr") && Integer.parseInt(parts[1]) >= 2020 && Integer.parseInt(parts[1]) <= 2030){
                        validField = true;
                    }

                    //hgt

                    else if(parts[0].equals("hgt")){
                        String onlyVal = parts[1].replaceAll("[^\\d.]", "");

                        if(parts[1].contains("cm") && Integer.parseInt(onlyVal) >= 150 && Integer.parseInt(onlyVal) <= 193){
                            validField = true;
                        }
                        else if(parts[1].contains("in") && Integer.parseInt(onlyVal) >= 59 && Integer.parseInt(onlyVal) <= 76){
                            validField = true;
                        }

                    }

                    //hcl

                    else if(parts[0].equals("hcl") && parts[1].startsWith("#") && parts[1].length() == 7) {
                            String withoutFirstChar = parts[1].replaceFirst("#", "");

                            String onlyLetters = withoutFirstChar.replaceAll("\\d", "");

                            String onlyNums = withoutFirstChar.replaceAll("[a-zA-Z]","");

                            String together = onlyNums + onlyLetters;

                            if(onlyLetters.matches("[a-g]+") && onlyNums.matches("[0-9]+") && together.length() == 6){
                                validField = true;
                            }
                        }

                    //ecl

                    else if(parts[0].equals("ecl") && parts[1].equals("amb") || parts[0].equals("ecl") && parts[1].equals("blu") || parts[0].equals("ecl") && parts[1].equals("brn") || parts[0].equals("ecl") && parts[1].equals("gry") || parts[0].equals("ecl") && parts[1].equals("grn") || parts[0].equals("ecl") && parts[1].equals("hzl") || parts[0].equals("ecl") && parts[1].equals("oth")){
                        validField = true;
                        }

                    //pid

                    else if(parts[0].equals("pid")){
                        String onlyVal = parts[1].replaceAll("[^\\d.]", "");
                        int length = onlyVal.length();

                        if(length == 9){
                        validField = true;
                        }
                    }


                    if(!listOfElements.get(a).isEmpty() && validField) {
                        passportData.put(parts[0], parts[1]);
                    }
                    else if(listOfElements.get(a).isEmpty()){
                        passportData.clear();
                        System.out.println(valid);
                    }


                        if (passportData.size() == 8 && validField) {
                            valid = valid + 1;
                            passportData.clear();
                        } else if (passportData.size() == 7 && !passportData.containsKey("cid") && validField) {
                            valid = valid + 1;
                            passportData.clear();
                        } else {

                        }

                    System.out.println(listOfElements.get(a));
            }
        }
        System.out.println(valid);
    }
}
