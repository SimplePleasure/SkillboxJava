public class Countries {


    String country;
    String capital;
    String currency;
    int area;
    int population;

    Countries(String country, String capital, String currency, int area, int population) {
        this.country = country;
        this.capital = capital;
        this.currency = currency;
        this.area = area;
        this.population = population;
    }


    @Override
    public String toString() {
        return country;
    }


}
