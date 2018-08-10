

import com.ust.transein.bmw.model.Authority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionTutrial {
    private String CONST ="JKS";
    public static void main(String argsp[]){

        Collection<TestModel> authorities=new ArrayList<>();
        TestModel t1=new TestModel(1,"jks");
        TestModel t2=new TestModel(2,"jithu");
        authorities.add(t1);
        authorities.add(t2);
        //System.out.println(authorities.toString());
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
