class UserPreferences {

    int budget; // pesos

     int availableTimeInDays;
    int startTime;
    int endTime;

    int availableTime;
    public UserPreferences(int budget, int availableTimeinDays,int startTime,int endTime){
        this.budget = budget;
        this.availableTimeInDays = availableTimeInDays;
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableTime = (endTime - startTime) * availableTimeInDays;
    }

}
