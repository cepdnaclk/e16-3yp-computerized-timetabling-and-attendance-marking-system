package Group10.example.API.Model;

public class Result {
    private String result1;
    private String result2;
    private String result3;

    public Result(String result1) {
        this.result1 = result1;
    }

    public Result(String result1, String result2) {
        this.result1 = result1;
        this.result2 = result2;
    }

    public Result(String result1, String result2, String result3) {
        this.result1 = result1;
        this.result2 = result2;
        this.result3 = result3;
    }

    public String getResult1() {
        return result1;
    }

    public String getResult2() {
        return result2;
    }

    public String getResult3() {
        return result3;
    }
}
