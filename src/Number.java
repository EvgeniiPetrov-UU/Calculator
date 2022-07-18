public enum Number {
    C(100, "C"), XC(90, "XC"), L(50, "L"), XL(40, "XL"),
    X(10, "X"), IX(9, "IX"), V(5, "V"), IV(4, "IV"), I(1, "I");
    //������� ��������� � �������� �������, ����� ����� �� ����������� �� ����������.
    // ��� ���������� ��� ����������� � ������� �����

    private int arabValue;
    private String str;

    Number(int arabValue, String str){
        this.arabValue = arabValue;
        this.str = str;
    }

    public int getValue(){
        return arabValue;
    }
    public String getStr(){
        return str;
    }
}
