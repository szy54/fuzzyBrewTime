import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;
import net.sourceforge.jFuzzyLogic.ruleAggregation.*;

public class FuzzyBrewTime {

    /*
        brewing time fuzzy controller for the Aeropress by Aerobie
        author: Szymon Kurdziel
     */

    public static void main(String[] args) throws Exception {
        try {
            //String fileName = "src/brewTime2.fcl";
            String fileName = args[0];
            int grindSize = Integer.parseInt(args[1]);
            int waterTemp = Integer.parseInt(args[2]);
            double stirring = Double.parseDouble(args[3]);
            FIS fis = FIS.load(fileName, false);

            FuzzyRuleSet fuzzyRuleSet = fis.getFuzzyRuleSet();
            fuzzyRuleSet.chart();

            //grindsize "clicks" for Zassenhaus Quito grinder (0 .. 45]
            fuzzyRuleSet.setVariable("grindsize", grindSize);
            //water temperature in Celcius [0 .. 100]
            fuzzyRuleSet.setVariable("watertemp", waterTemp);
            //stirs with a teaspoon [0 .. 15]
            fuzzyRuleSet.setVariable("stirring", stirring);

            fuzzyRuleSet.evaluate();

            fuzzyRuleSet.getVariable("brewtime").chartDefuzzifier(true);


        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Wrong argument list. Example: java FuzzyBrewTime int<grindsize> int<watertemp> double<stirring>");
        } catch (NumberFormatException ex) {
            System.out.println("Wrong format. Example: java FuzzyBrewTime int<grindsize> int<watertemp> double<stirring>");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.toString());
        }
    }


}
