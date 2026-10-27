package org.example;

public class Park {
    String parkName;

    public Park(String parkName) {
        this.parkName = parkName;
    }

    void printPark() {
        System.out.println("Название парка: " + parkName + "\n");
    }

    public class Ride {
        String rideName;
        String rideSchedule;
        int ridePrice;

        public Ride(String rideName, String rideSchedule, int ridePrice) {
            this.rideName = rideName;
            this.rideSchedule = rideSchedule;
            this.ridePrice = ridePrice;
        }

        void printRide() {
            System.out.println("Название аттракциона: " + rideName + "\nВремя работы: " + rideSchedule +
                    "\nСтоимость: " + ridePrice + "\n");
        }
    }

}