public class Email {
    protected String username,domain,extension;

    public Email(){
        username=domain=extension=null;
    }
    public Email(String username,String domain, String ext) {
        this.username=username;
        this.domain=domain;
        this.extension=ext;
    }
    public int getCode(){
        return 0;
    }

    public String getUsername(){return username;}
    public String getDomain(){return domain;}
    public String getExt(){return extension;}

    public String toString()
    {
        return username + "@" + domain + "." +extension;
    }
}


class UniversityEmail extends Email {
    private String department;
    private int code;

    UniversityEmail() {
        super();
        department=null;
        code=0;
    }

    UniversityEmail(String department, int code) {
        this.department = department;
        this.code = code;
    }

    UniversityEmail(String a, String b, String c, String department) {
        super(a,b,c);
        this.department=department;
        assignCode();
    }

    private void assignCode() {
        if(department!=null)
        {
            switch(department){
                case "art":
                    code=1;
                    break;
                case "chee":
                    code=2;
                    break;
                case "chem":
                    code=3;
                    break;
                case "coe":
                    code=4;
                    break;
                case "cs":
                    code=5;
                    break;
                case "egr":
                    code=6;
                    break;
                case "polsci":
                    code=7;
                    break;
            }
        }
    }

    public int getCode(){
        return code;
    }

    public String toString()
    {
        return username + "@" + department + "." + domain + "." + extension;
    }
}