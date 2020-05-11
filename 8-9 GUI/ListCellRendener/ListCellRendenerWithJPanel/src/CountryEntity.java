public class CountryEntity {

    String countryName;
    String countryIndex;

    CountryEntity(String name, String index) {
        countryName = name;
        countryIndex = index;
    }


    public String getCountryName() {
        return countryName;
    }

    public String getCountryIndex() {
        return countryIndex;
    }


}
