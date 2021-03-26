/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangndx.errors;

/**
 *
 * @author Admin
 */
public class LoginErrorDTO {
    private String errorusername;
    private String errorpassword;

    public LoginErrorDTO() {
    }

    public LoginErrorDTO(String errorusername, String errorpassword) {
        this.errorusername = errorusername;
        this.errorpassword = errorpassword;
    }

    public String getErrorusername() {
        return errorusername;
    }

    public void setErrorusername(String errorusername) {
        this.errorusername = errorusername;
    }

    public String getErrorpassword() {
        return errorpassword;
    }

    public void setErrorpassword(String errorpassword) {
        this.errorpassword = errorpassword;
    }
    
    
}
