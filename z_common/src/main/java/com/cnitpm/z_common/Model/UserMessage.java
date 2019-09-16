package com.cnitpm.z_common.Model;

import java.io.Serializable;

public class UserMessage implements Serializable {

    /**
     * sf : 普通会员
     * uVAO : {"Uid":3174,"Username":"13085486819","Realname":"","Vip":0,"ExamID":45,"ExamZYID":0,"ExamJBID":0,"Role":0,"Password":"","Rolestr":null,"Pmb":0,"Adminrole":0,"Loginfou":1,"Userpic":null,"Vipstr":",0,","Openid":null,"Isws":1}
     */

    private String sf;
    private UVAOBean uVAO;

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public UVAOBean getUVAO() {
        return uVAO;
    }

    public void setUVAO(UVAOBean uVAO) {
        this.uVAO = uVAO;
    }

    public class UVAOBean implements Serializable{
        /**
         * Uid : 3174
         * Username : 13085486819
         * Realname :
         * Vip : 0
         * ExamID : 45
         * ExamZYID : 0
         * ExamJBID : 0
         * Role : 0
         * Password :
         * Rolestr : null
         * Pmb : 0
         * Adminrole : 0
         * Loginfou : 1.0
         * Userpic : null
         * Vipstr : ,0,
         * Openid : null
         * Isws : 1
         */

        private int Uid;
        private String Username;
        private String Realname;
        private int Vip;
        private int ExamID;
        private int ExamZYID;
        private int ExamJBID;
        private int Role;
        private String Password;
        private Object Rolestr;
        private int Pmb;
        private int Adminrole;
        private double Loginfou;
        private Object Userpic;
        private String Vipstr;
        private Object Openid;
        private int Isws;

        public int getUid() {
            return Uid;
        }

        public void setUid(int Uid) {
            this.Uid = Uid;
        }

        public String getUsername() {
            return Username;
        }

        public void setUsername(String Username) {
            this.Username = Username;
        }

        public String getRealname() {
            return Realname;
        }

        public void setRealname(String Realname) {
            this.Realname = Realname;
        }

        public int getVip() {
            return Vip;
        }

        public void setVip(int Vip) {
            this.Vip = Vip;
        }

        public int getExamID() {
            return ExamID;
        }

        public void setExamID(int ExamID) {
            this.ExamID = ExamID;
        }

        public int getExamZYID() {
            return ExamZYID;
        }

        public void setExamZYID(int ExamZYID) {
            this.ExamZYID = ExamZYID;
        }

        public int getExamJBID() {
            return ExamJBID;
        }

        public void setExamJBID(int ExamJBID) {
            this.ExamJBID = ExamJBID;
        }

        public int getRole() {
            return Role;
        }

        public void setRole(int Role) {
            this.Role = Role;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public Object getRolestr() {
            return Rolestr;
        }

        public void setRolestr(Object Rolestr) {
            this.Rolestr = Rolestr;
        }

        public int getPmb() {
            return Pmb;
        }

        public void setPmb(int Pmb) {
            this.Pmb = Pmb;
        }

        public int getAdminrole() {
            return Adminrole;
        }

        public void setAdminrole(int Adminrole) {
            this.Adminrole = Adminrole;
        }

        public double getLoginfou() {
            return Loginfou;
        }

        public void setLoginfou(double Loginfou) {
            this.Loginfou = Loginfou;
        }

        public Object getUserpic() {
            return Userpic;
        }

        public void setUserpic(Object Userpic) {
            this.Userpic = Userpic;
        }

        public String getVipstr() {
            return Vipstr;
        }

        public void setVipstr(String Vipstr) {
            this.Vipstr = Vipstr;
        }

        public Object getOpenid() {
            return Openid;
        }

        public void setOpenid(Object Openid) {
            this.Openid = Openid;
        }

        public int getIsws() {
            return Isws;
        }

        public void setIsws(int Isws) {
            this.Isws = Isws;
        }

        @Override
        public String toString() {
            return "UVAOBean{" +
                    "Uid=" + Uid +
                    ", Username='" + Username + '\'' +
                    ", Realname='" + Realname + '\'' +
                    ", Vip=" + Vip +
                    ", ExamID=" + ExamID +
                    ", ExamZYID=" + ExamZYID +
                    ", ExamJBID=" + ExamJBID +
                    ", Role=" + Role +
                    ", Password='" + Password + '\'' +
                    ", Rolestr=" + Rolestr +
                    ", Pmb=" + Pmb +
                    ", Adminrole=" + Adminrole +
                    ", Loginfou=" + Loginfou +
                    ", Userpic=" + Userpic +
                    ", Vipstr='" + Vipstr + '\'' +
                    ", Openid=" + Openid +
                    ", Isws=" + Isws +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "sf='" + sf + '\'' +
                ", uVAO=" + uVAO +
                '}';
    }
}
