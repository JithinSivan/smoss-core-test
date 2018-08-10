package com.ust.transein.bmw.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollectionTutrial {

    public static void main(String argsp[]){

        List<TestModel> authorities=new ArrayList<>();
        TestModel t1=new TestModel(1,"jks");
        TestModel t2=new TestModel(2,"jithu");
        authorities.add(t1);
        authorities.add(t2);
        final Iterator<TestModel> iteratorexample=authorities.iterator();
        while(iteratorexample.hasNext()){
            TestModel testModel=iteratorexample.next();
            if(testModel.id>=2){
                System.out.println(testModel);
            }
            else{
                iteratorexample.remove();
            }

        }


        System.out.println("%%%%%%%%");
       /* for(TestModel tst : authorities){
          //  System.out.println(tst);
            if(tst.id>=2){
                System.out.println(tst);
            }
            else{
                authorities.remove(tst);
            }
        }
        */


    }
}
