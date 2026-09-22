package lw01.unguided;

public class CarWash extends WashService {
    public CarWash(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int days = getDays();
        int charge = 0;
        if (days <= 3) {
            charge = days * 35000;
        } else {
            charge = (3 * 35000) + ((days - 3) * 25000);
        }
        return charge + 15000; // setup fee
    }

    @Override
    public String label() {
        return "Car";
    }
}
