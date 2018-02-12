
package homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

class Predicate implements Cloneable
{
    boolean containsNeg;
    int numberOfVaribales;
    int numberOfConstants;
    String predicateName;
    String printPredicate;
    ArrayList<String> constants;
    ArrayList<String> variables;
    ArrayList<String> arguments;
    
    Predicate()
    {
        containsNeg=false;
        numberOfVaribales=0;
        numberOfConstants=0;
        predicateName = "";
        printPredicate="";
        constants=new ArrayList<String>();
        variables = new ArrayList<String>();
        arguments = new ArrayList<String>();
        
    }
    
    public Object clone()throws CloneNotSupportedException
    {
        Predicate newPredicate = (Predicate)super.clone();
        newPredicate = new Predicate();
        
        newPredicate.containsNeg = this.containsNeg;
        newPredicate.numberOfVaribales = this.numberOfVaribales;
        newPredicate.numberOfConstants = this.numberOfConstants;
        newPredicate.predicateName= this.predicateName;
        newPredicate.printPredicate= this.printPredicate;
       
        newPredicate.constants = new ArrayList<String>();
        for(String x : this.constants)
            newPredicate.constants.add(x);        
        newPredicate.variables = new ArrayList<String>();
        for(String x : this.variables)
            newPredicate.variables.add(x);

        newPredicate.arguments = new ArrayList<String>();
        for(String x : this.arguments)
            newPredicate.arguments.add(x);

        
        return newPredicate;
    }
}

class Box implements Cloneable
{
    int sentence;
    Predicate predicate;
    
    Box()
    {
        sentence=0;
        predicate= new Predicate();
    }
    
    public Object clone()throws CloneNotSupportedException
    {
        Box newBox = (Box)super.clone();
        newBox= new Box();
        newBox.sentence=this.sentence;
        return newBox;
    }
    
}

class PredicateLocation implements Cloneable
{
    HashMap<String,ArrayList<Box>> map;
    
    PredicateLocation()
    {
        map = new HashMap<String,ArrayList<Box>>();
    }
    
    public Object clone()throws CloneNotSupportedException
    {
        PredicateLocation newPredicateLocation= (PredicateLocation)super.clone();
        newPredicateLocation = new PredicateLocation();
        newPredicateLocation.map= new HashMap<String, ArrayList<Box>>();
        return newPredicateLocation;
    }
     
}

class KnowledgeBase implements Cloneable
{
    int count;
//    String predicateName;
    HashMap<Integer,ArrayList<Predicate>> sentences;
    HashMap<Integer,Boolean> sentenceUsed;
    
    KnowledgeBase()
    { 
        count = 0;
        sentences = new HashMap<Integer,ArrayList<Predicate>>();
        sentenceUsed  = new HashMap<Integer,Boolean>();
        
    }
    
    void add(int n ,String current)
    {
//        this();
//        count = y;
        count++;
//        sentences.put(n,current);
        sentenceUsed.put(n,false);
    }
    
    public Object clone()throws CloneNotSupportedException
    {
        
        KnowledgeBase newKnowkedgeBase = (KnowledgeBase)super.clone();
        newKnowkedgeBase = new KnowledgeBase();
        newKnowkedgeBase.sentences = new HashMap<Integer,ArrayList<Predicate>>();
        newKnowkedgeBase.sentenceUsed = new HashMap<Integer,Boolean>();
        
        for(int i =0; i< this.sentences.size();i++)
        {
            newKnowkedgeBase.sentences.put(i,this.sentences.get(i));
        } 
        
        for(int i =0; i< this.sentenceUsed.size();i++)
        {
            newKnowkedgeBase.sentenceUsed.put(i,this.sentenceUsed.get(i));
        }
        
        newKnowkedgeBase.count=this.count;
        return newKnowkedgeBase;
    }
    
}

public class Homework 
{
    
    public static int numberOfQueries = 0;
    public static int sizeOfKB = 0;
    
    public static void main(String[] args) throws IOException, CloneNotSupportedException
    {
        FileReader in = new FileReader("input.txt");
        StringBuffer sbf = new StringBuffer();
        BufferedReader br = new BufferedReader(in);
        numberOfQueries = Integer.parseInt(br.readLine());
        
        List<String> queries = new ArrayList<>();
        for(int i = 0 ;i <numberOfQueries;i++)
        {
//            System.out.println(i);
//            System.out.println(br.readLine()+"");
            queries.add(br.readLine());
        }
        
        sizeOfKB = Integer.parseInt(br.readLine());
                
        List<String> kbInput = new ArrayList<>();
        
        for(int i = 1 ;i <=sizeOfKB;i++)
        {
//            System.out.println(i);
//            System.out.println(br.readLine()+"");
//            inputKB.add(br.readLine());
            String temp = br.readLine();
            kbInput.add(temp);
//            preProcess(i,temp,kb,pl);
//            inputKB.add(temp);
        }
//        List<String> inputKB = new ArrayList<String>(); 
        

        for(int xx=0;xx<numberOfQueries;xx++)
        {
            String tempQuery = queries.get(xx);
        
            String tempu = negate(tempQuery);
//        tempQuery = "~"+tempQuery;
       
            
            KnowledgeBase kb= new KnowledgeBase();
            PredicateLocation pl = new PredicateLocation();
            preProcess(0, tempu, kb, pl);
            
            for(int l = 1; l<=sizeOfKB;l++)
            {
                preProcess(l, kbInput.get(l-1), kb, pl);
            }
        
//        KnowledgeBase clonedkb = (KnowledgeBase)kb.clone();
        
//        System.out.println(clonedkb.count+"kasaa");
//        
//        for(int i =0;i < clonedkb.sentences.size();i++)
//        {
//            clonedkb.sentences.get(i) = clonedkb.sentences.get(i).toLowerCase();
//            System.out.println(clonedkb.sentences.get(i));
//        }
//        System.out.println("Cloned" + clonedkb.hashCode());
//        System.out.println("Original" + kb.hashCode());
//        clonedkb.sentences.add("Chit");
//        kb.sentences.add("Lind");
        System.out.println("KB IS HEREEEE");
//        System.out.println(unify(kb,pl,1,2) + "UNIFICATION KA VALUE");

        System.out.println("^^^^^^^^^^^^^^^^^^^^^^");
        print(kb);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("KBBBBBBBBBBBBB");
        
        boolean result = resolution(kb,pl,0);
        String str = String.valueOf(result);
        str = str.toUpperCase();
        
        sbf.append(str);
        if(xx!= numberOfQueries-1)
            sbf.append(System.lineSeparator());
        
//            System.out.println("_____________________");
//        System.out.println(sbf);
//            System.out.println("_____________________");
        
        
        System.out.println(result + "<= RESOLUTION KA ULTIMATE RESULT");
        
        print(kb);
        
        System.out.println("Queries");
        for(String s : queries)
            System.out.println(s);
        
        System.out.println(numberOfQueries);
        
//        ArrayList<Predicate> t = kb.sentences.get(2);
//        for(Predicate tx :t )
//        {
//            System.out.println(tx.predicateName);
//        }
        
        
//        canUnify(s1,s2);
        }
        
        BufferedWriter bwr = new BufferedWriter(new FileWriter(new File("output.txt")));
        
        bwr.write(sbf.toString());
        bwr.flush();
        bwr.close();
        
        
        
    }
    
    static void print(KnowledgeBase kb)
    {
        for(int i =0;i<kb.sentences.size();i++)
        {
            System.out.println(kb.sentenceUsed.get(i) + " for sentence " + i);
            ArrayList <Predicate> pred = new ArrayList<Predicate>();
            pred = kb.sentences.get(i);
            System.out.println("Sentence Number is :" + i);
            for(int xx= 0;xx<pred.size();xx++)
            {
                System.out.println(pred.get(xx).printPredicate);
                System.out.println(pred.get(xx).arguments);
            }
            
//            System.out.println("I" + i +"Sent " + kb.sentences.get(i));
        }
    }
    
    
    public static boolean checkTermination(KnowledgeBase kb)
    {
        for(int i =0;i<kb.sentenceUsed.size();i++)
        {
            if(!kb.sentenceUsed.get(i))
                return false;
        }
        return true;
    }
    
    public static boolean checkNull(KnowledgeBase kb)
    {
        for(int i =0; i<kb.sentences.size();i++)
        {
            if(kb.sentences.get(i).isEmpty()==true)
                return true;
            
        }
        return false;
    }
    
    public static boolean resolution(KnowledgeBase kb,PredicateLocation pl,int i) throws CloneNotSupportedException
    {
        if(checkNull(kb))
        {
            System.out.println("KEM CHOOOOO");
            return true;
        }
        
        for(int x=0;x<kb.sentences.size();x++)
        {
                if( !kb.sentenceUsed.get(x) && unify(kb,pl,i,x))
                {
                    System.out.println("PRINTING THE KB AFTER UNIFICATION OF ::: " + i + " &&& " + x);
                    System.out.println("*****************");
                    print(kb);
                    System.out.println("*****************");
                    
//                    KnowledgeBase kb1 = (KnowledgeBase)kb.clone();
                    
                    System.out.println("In Unification of x " + x + " I " +i);
                    boolean flag1 = resolution(kb,pl,kb.sentences.size()-1);
                    
                    if(checkTermination(kb))
                        return false;
                    
                    System.out.println("Im here, after the recurssion call");
                    if(flag1)
                    {    
                        return true;
                    }
                    else
                    {
                        kb.sentences.remove(kb.sentences.size()-1);
                        kb.sentenceUsed.put(x, false);
                        kb.sentenceUsed.put(i, false);
                    }
                }
        }
        return false;
    }
    

    public static boolean unify(KnowledgeBase kb, PredicateLocation pl,int i, int j) throws CloneNotSupportedException
    {
        
        //write a code for checking if its true
        
//        if(kb.sentenceUsed.get(i) || kb.sentenceUsed.get(j))
//            return false;
        
        if(i==j)
            return false;
        
        
        System.out.println("Size of KB => " + kb.sentences.size() + " and I => " + i + " J=> " +j);
        
        ArrayList<Predicate> list1= new ArrayList<>();
        ArrayList<Predicate> list2= new ArrayList<>();
        
        for(Predicate p: kb.sentences.get(i))
            list1.add((Predicate) p.clone());
        
        for(Predicate p: kb.sentences.get(j))
            list2.add((Predicate) p.clone());
        
//        list1 = kb.sentences.get(i);
//        list2 = kb.sentences.get(j);
        
        
        System.out.println(list1.hashCode() + " LOOOOOOOOOOOL " + kb.sentences.get(i).hashCode());
//        
//        if((list1.size()==0) || (list2.size()==0))
//            return false;
        
        ArrayList<Predicate> result = new ArrayList<Predicate>();
      
        for(int x =0; x< list1.size();x++)
        {
            result.add(list1.get(x));
        }
        for(int x =0; x< list2.size();x++)
        {
            result.add(list2.get(x));
        }
        
        for(int x = 0; x < list1.size();x++)
        {
            for(int y = 0; y < list2.size();y++)
            {
//                System.out.println(negate(list1.get(x).predicateName) + "hdbsfkajbfjbf");
//                System.out.println(list2.get(y).predicateName + "hdbsf");
                if(((list1.get(x).predicateName).equals(list2.get(y).predicateName)) && (((list1.get(x).containsNeg) && !(list2.get(y).containsNeg)) || ((!list1.get(x).containsNeg && (list2.get(y)).containsNeg))))
                {   
//                    System.out.println("HIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
                    HashMap <String, String> map = new HashMap<>();
                    System.out.println("True for " + list1.get(x).printPredicate + " for X " + x +" ANNDDD "+ list2.get(y).printPredicate+ " for Y " + y);
//                    System.out.println("Supp");
                    for(int z =0 ; z< list1.get(x).arguments.size();z++)
                    {
                        if(Character.isUpperCase(list1.get(x).arguments.get(z).charAt(0)) && Character.isLowerCase(list2.get(y).arguments.get(z).charAt(0)))
                        {
                            if(map.containsKey(list2.get(y).arguments.get(z)) && !(list1.get(x).arguments.get(z).equals(map.get(list2.get(y).arguments.get(z)))))
                                return false;
                            
                            map.put(list2.get(y).arguments.get(z), list1.get(x).arguments.get(z));
                        }
                        else if(Character.isUpperCase(list2.get(y).arguments.get(z).charAt(0)) && Character.isLowerCase(list1.get(x).arguments.get(z).charAt(0)))
                        {
                            if(map.containsKey(list1.get(x).arguments.get(z)) && !(list2.get(y).arguments.get(z).equals(map.get(list1.get(x).arguments.get(z)))))
                                return false;
                            map.put(list1.get(x).arguments.get(z), list2.get(y).arguments.get(z));
                        }
                        else if(Character.isLowerCase(list1.get(x).arguments.get(z).charAt(0)) && Character.isLowerCase(list2.get(y).arguments.get(z).charAt(0)))
                        {
//                            //Var x to Var x chahiye toh isko COMMENT KARO
//                            if(list1.get(x).arguments.get(z).equals(list2.get(y).arguments.get(z)))
//                            {
//                                System.out.println("I failed to unify ");
//                                return false;
//                            }
                            map.put(list1.get(x).arguments.get(z), list2.get(y).arguments.get(z));
                        }
                        else if(Character.isUpperCase(list1.get(x).arguments.get(z).charAt(0)) && Character.isUpperCase(list2.get(y).arguments.get(z).charAt(0))) 
                        {
                            System.out.println("UPPER CASE 1 : " + list1.get(x).arguments.get(z));
                            System.out.println("UPPER CASE 2 : " + list2.get(y).arguments.get(z));
                            if(!list1.get(x).arguments.get(z).equals(list2.get(y).arguments.get(z)))
                            {
                                System.out.println("I failed to unify ");
                                return false;
                            }
                        }
                    }
                    
                    System.out.println("#########");
                    System.out.println(map);
                    
                    
//                    for(int l = 0; l <map.size();l++)
//                    {
//                        for(int m =0; m < map.size();m++)
//                        {
//                            if(map.containsKey(map.get(l)))
//                            {
//                                map.put(map.get(l),)
//                            }
//                        }
//                    }
                    
                    
                    for(int l = 0; l<result.size();l++)
                    {
                        for(int m=0;m<result.get(l).arguments.size();m++)
                        {
                            if(map.containsKey(result.get(l).arguments.get(m)))
                            {
                                result.get(l).arguments.set(m, map.get(result.get(l).arguments.get(m)));
                            }
                            
                        }
                    }
                    
                    
                    
//                    System.out.println("----------------");
                    
//                    for(Predicate p: result)
//                    {
//                        
//                    }
                    //To remove x and y                    
                    result.remove(list1.get(x));
                    boolean flag = true;
                    for(int r = 0;r<result.size();r++)
                    {
                        for(int ll=r+1;ll<result.size();ll++)
                        {
                            if(result.get(ll).predicateName.equals(result.get(r).predicateName) && (result.get(ll).containsNeg == result.get(r).containsNeg))
                            {
                                for(int v=0; v<result.get(r).arguments.size();v++)
                                {
                                    if(!result.get(ll).arguments.get(v).equals(result.get(r).arguments.get(v)))
                                    {
                                        flag = false;
                                    }
                                }
                                if(flag)
                                {
                                    System.out.println("//////////////////////////////////////////////");
                                    result.remove(ll);
                                }
                            }
                        }
                    }
                        
                    result.remove(list2.get(y));
                    
                    boolean flag1 = true;
                    for(int r = 0;r<result.size();r++)
                    {
                        for(int ll = r+1; ll<result.size();ll++)
                        {
                            if(result.get(ll).predicateName.equals(result.get(r).predicateName) && (result.get(ll).containsNeg == result.get(r).containsNeg))
                            {
                                for(int v=0; v<result.get(r).arguments.size();v++)
                                {
                                    if(!result.get(ll).arguments.get(v).equals(result.get(r).arguments.get(v)))
                                    {
                                        flag1 = false;
                                    }
                                }
                                if(flag1)
                                {
                                    System.out.println("//////////////////////////////////////////////");
                                    result.remove(ll);
                                }
                            }
                        }
                    }    
                    if(flag1 || flag)
                    {
                        for(Predicate p : result)
                        {
                            System.out.println("[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[");
                            System.out.println(p.printPredicate);
                            System.out.println(p.arguments);
                            System.out.println("]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]");
                        }
                    }
                    kb.sentenceUsed.put(i, true);
                    kb.sentenceUsed.put(j, true);
                    
                    kb.sentenceUsed.put(kb.sentences.size(),false);
                    kb.sentences.put(kb.sentences.size(), result);
                    return true;
        
                }
//                else
//                {
//                    System.out.println("HELOOOOOOOOOOOOOOOOOOOOOOOOOO" + x +" HIIIIIIIIIIIIIII" + y);
//                    if(x==list1.size() || y==list2.size())
//                    {
//                        System.out.println("I FAILED AGAIN AS I REACHED MY END");
//                        return false;
//                    }
//                }
            }
        }
        
//        for(Predicate p : result)
//            System.out.println(p.printPredicate + "LELO");
//        
//        String []splited1;
//        String []splited2;
//        if(s1.contains("|"))
//        {
//            splited1 = s1.split(" \\| ");
//        }
//        else
//        {
////            splited = new String[100];
//            splited1 = new String[1];
//            splited1[0] = s1;   
//        }   
//        for(int x =0;x<splited1.length;x++)
//        {   
//            int start = splited1[x].indexOf("(");
//            String temp = splited1[x].substring(0, start);
//            temp = temp.trim();
//            if(pl.map.containsKey("~"+temp))
//            {
//                
//            }
//            
//            System.out.println(splited[x] + "BABA" + " I "+x);
//            
//            System.out.println(splited[x] + "LavDA");
//            
            return false;
    }
    
    public static void preProcess(int i, String s, KnowledgeBase kb, PredicateLocation pl)
    {
        
        kb.add(i,s);
        String []splited;
        if(s.contains("|"))
        {
            splited = s.split(" \\| ");
        }
        else
        {
//            splited = new String[100];
            splited = new String[1];
            splited[0] = s;
            
        }
            
        for(int x =0;x<splited.length;x++)
        {
//            System.out.println(splited[x] + "BABA" + " I "+i);
            int start = splited[x].indexOf("(");
//            System.out.println(splited[x] + "LavDA");
            String temp = splited[x].substring(0, start);
            temp = temp.trim();
//            System.out.println("JHATA" + temp);
            Predicate predicate = new Predicate();
            predicate.printPredicate= temp;
            if(temp.charAt(0)=='~')
                predicate.containsNeg = true;
            if(predicate.containsNeg)
                temp=temp.replaceFirst("~", "");
            predicate.predicateName = temp;
            
//            System.out.println(predicate.containsNeg + " <- Does it contain Negative for Predicate " + temp);
            
            int startS1=splited[x].indexOf("(");
            int endS1=splited[x].indexOf(")");
//            System.out.println("Start " + startS1);
//            System.out.println("End "  + endS1);
            String temp1 = splited[x].substring(startS1+1,endS1);
            
            String arguments[] = temp1.split(",");
            
            for(int z = 0;z<arguments.length;z++)
            {
//                System.out.println("Arguments is " + arguments[z]);
                predicate.arguments.add(arguments[z]);
                for(int ii =0; ii<predicate.arguments.size();ii++)
                {
//                    System.out.println("Arguments are : " + predicate.arguments.get(ii));
                }
                
                if(Character.isUpperCase(arguments[z].charAt(0)))
                {
                    predicate.constants.add(arguments[z]);
                    predicate.numberOfConstants++;
                    for(int ii =0; ii<predicate.constants.size();ii++)
                    {
//                        System.out.println("Constants are : " + predicate.constants.get(ii));
                    }
                }
                else
                {
                    predicate.variables.add(arguments[z]);
                    predicate.numberOfVaribales++;
                    for(int ii =0; ii<predicate.variables.size();ii++)
                    {
//                        System.out.println("Variables are : " + predicate.variables.get(ii));
                    }
                }
            }
            if(kb.sentences.containsKey(i))
            {
                kb.sentences.get(i).add(predicate);
//                System.out.println("In IF of KB Addition" + predicate.predicateName);
            }
            else
            {
                ArrayList<Predicate> predicates = new ArrayList<>();
                predicates.add(predicate);
                kb.sentences.put(i, predicates);
//                System.out.println("In ELSE of KB Addition" + predicate.predicateName);
                
            }
            Box box = new Box();
            box.sentence = i;
            //Check for PROBLEMS of DEEP COPY
            box.predicate = predicate;
            
            if(pl.map.containsKey(temp))
            {
                pl.map.get(temp).add(box);
//                System.out.println("In IF");  
            }
            else
            {
                ArrayList<Box> boxes = new ArrayList<>();
                boxes.add(box);
                pl.map.put(temp, boxes);
//                System.out.println("In ELSE " + box.predicate.predicateName);  
            }
        }
        
//        System.out.println(pl.map.keySet());
        String predicateName;
        
//        System.out.println(kb.sentences.get(i)+"ada");
//        System.out.println(pl.map.size()+ " Size ");
      
    }
    
    public static String negate(String s)
    {
//        System.out.println("Hiiiiiiiiiiiiiii");
        if(s.charAt(0)=='~')
        {
//            System.out.println("Hiiiiiiiiiiiiiii");
            return s.substring(1,s.length());
        }
        s="~"+s;
//        System.out.println(s + "bbbb");
        return s; 
    } 
}