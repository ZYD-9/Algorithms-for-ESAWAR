class UserPreferences {
    int budget; // pesos
    int availableTime; // in hours

    int startTime;
    int endTime;
    public UserPreferences(int budget, int availableTime,int startTime,int endTime){
        this.budget = budget;
        this.availableTime = availableTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
