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
public class UpdateErrorDTO {
    private String errorpassword;

    public UpdateErrorDTO() {
    }

    public UpdateErrorDTO(String errorpassword) {
        this.errorpassword = errorpassword;
    }

    public String getErrorpassword() {
        return errorpassword;
    }

    public void setErrorpassword(String errorpassword) {
        this.errorpassword = errorpassword;
    }
    
    
}
