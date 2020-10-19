import java.util.Iterator;

public class OfficeHoursQueue implements Iterable<OHRequest> {
    private OHRequest firstQueue;

    public OfficeHoursQueue (OHRequest queue) {
        firstQueue = queue;
    }

    @Override
    public Iterator<OHRequest> iterator() {
        return new TYIterator(firstQueue);
    }

    public static void main(String [] args) {
        OHRequest s1 = new OHRequest("Failing my test for get in arrayDeque, NPE", "Pam", null);
        OHRequest s2 = new OHRequest("conceptual: what is dynamic method selection", "Michael", s1);
        OHRequest s3 = new OHRequest("git: what does checkout do.", "Jim", s2);
        OHRequest s4 = new OHRequest("help", "Dwight", s3);
        OHRequest s5 = new OHRequest("debugging get(i)", "Creed", s4);

        OfficeHoursQueue OHqueue = new OfficeHoursQueue(s5);
        for (OHRequest request : OHqueue) {
            System.out.println(request.name);
        }

        OHRequest t1 = new OHRequest("help me", "Jack", null);
        OHRequest t2 = new OHRequest("im bored", "Michael", t1);
        OHRequest t3 = new OHRequest("thank u", "Henri", t2);
        OHRequest t4 = new OHRequest("thank u", "Henri", t3);

        OfficeHoursQueue OHqueue2 = new OfficeHoursQueue(t4);
        for (OHRequest request : OHqueue2) {
            System.out.println(request.name);
        }
    }
}
