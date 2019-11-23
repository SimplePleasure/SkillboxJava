public class Country
{
    private String countryName;
    private String countryCode;

    public Country(String name, String code) {
        countryName = name;
        countryCode = code;
    }
    public String getCountryName(){
        return countryName;
    }
    public String getCountryCode(){
        return countryCode;
    }
    public void setCountryName(String name){
        countryName = name;
    }
    public void setCountryCode(String code){
        countryCode = code;
    }


    @Override
    public String toString() {
        return getCountryName();
    }
}
