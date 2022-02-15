package week8;
import java.util.*;


public class NumberOfAtoms {
		private int pos=0;
		//function to fetch the atoms and count and append all of it to a single string
	    public String getFormula(String formula) {
	        
	    	//creating a string builder instead of string because it is mutable and we need to dynamically add
	    	//atoms and their counted value to the resulting string
	    	StringBuilder res = new StringBuilder();
	    	
	    	//Creating a map which will store the atom name as key and number as value
	    	//the function which is called will return the calculated map of formula
	    	//the input string is converted into character array so that it will be easier to loop through them
	        Map<String, Integer> finalFormula = countAtoms(formula.toCharArray());
	        
	        //Looping through the map in order to append atoms and numbers into the string builder
	        for (String element: finalFormula.keySet()) {
	            res.append(element);
	            int count = finalFormula.get(element);
	            //if the number of atoms is exactly 1 there is no need to append 1 to the result hence we skip that
	            //and only append if the count is greater than 1
	            if (count > 1) {
	            	res.append("" + count);
	            }
	        }
	        return res.toString();
	    }
	    
	    //function to calculate the number of atoms and store in the map with corresponding atom name
	    private Map<String, Integer> countAtoms(char[] f) {
	    	
	    	//a tree map which will store the final calculated atom and numbers
	    	//tree map is used because it stores the key-values in the sorted order by default
	        Map<String, Integer> solved = new TreeMap<String, Integer>();
	        
	        //looping through the entire character array
	        while (pos != f.length) {
	        	//if the character is '(' then we store the following atoms in an another map in order to multiply
	        	//them with a number if enocuntered any after the closing paranthesis ')'
	            if (f[pos] == '(') {
	                pos++;
	                Map<String, Integer> tmp = countAtoms(f);
	                int count = getCount(f);
	                for (Map.Entry<String, Integer> entry : tmp.entrySet())
	                    solved.put(entry.getKey(),entry.getValue() * count);
	              
                
	                
	            }
	            //if the character is ')' then we return the calculated map of formula
	            else if (f[pos] == ')') {
	                ++pos;
	                return solved;
	            } 
	            //if anything else is found then it means it is an atom which does not require any more calculation
	            //therefore we add them into the solved tree map
	            else {
	                String name = getName(f);
	                solved.put(name,getCount(f));

	            }
	        }
	        return solved;
	    }
	    
	    
	    //function to get the name of atom
	    private String getName(char[] f) {
	        String name = "" + f[pos];
	        pos++;
	        while (pos < f.length && 'a' <= f[pos] && f[pos] <= 'z') {
	        	name += f[pos];
	        	pos++;
	        }
	        return name;
	    }
	    
	    //function to get the number of atoms or in another case the multiplier
	    private int getCount(char[] f) {
	        int count = 0;
	        while (pos < f.length && '0' <= f[pos] && f[pos] <= '9') {
	            count = count * 10 + (f[pos] - '0');
	            pos++;
	        }
	        return count == 0 ? 1 : count;
	    }
	    
	    public static void main(String[] args) {
			NumberOfAtoms obj= new NumberOfAtoms();
			String res = obj.getFormula("Mg3(OH2)2");
			System.out.println(res);
		}
}