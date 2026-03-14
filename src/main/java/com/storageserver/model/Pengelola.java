package com.storageserver.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set; 

/**
 * (c) Alegrarsio Gifta Lesmana
 * com.storageserver.model.Pengelola
 * Representasi data dan record dari tabel 'pengelola'
 */

@Data
@Entity
@Table(name = "pengelola")
public class Pengelola {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @Column(nullable = false)
    private String nama;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; 
    
    /**
     * Relasi Many-to-One ke dirinya sendiri (atasan)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") 
    private Pengelola parent;

    
    /**
     * Relasi One-to-Many ke dirinya sendiri (bawahan)
     * 'mappedBy = "parent"' menunjuk ke field 'parent' di atas
     */
    @OneToMany(mappedBy = "parent")
    private Set<Pengelola> children;


    /**
     * GetNama() -> return 
     * @return
     */
    public String getNama(){
        return this.nama;
    }

    /**
     * GetEmail() -> return
     * @return
     */

    public String getEmail(){
        return this.email;
    }

    /**
     * Get Password() -> return
     * @return
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * getId() -> return
     * @return
     */
    public Integer getId(){
        return this.id;
    }

    /**
     * setPassword()
     * Setter function for the password
     * @param password
     */

    public void setPassword(String password){
        this.password = password;
    }


    /**
     * setUserName()
     * Setter function for the username
     * @param username
     */

    public void setUserName(String username){
        this.nama = username;
    }



    /**
     * isValidPassword()
     * Validation method for password
     * @param password
     * @return
     */

    public boolean isValidPassword(String password){
        return (password.length() >= 8);
    }

    public int findByName(String nama){
        return this.nama.equals(nama) ? 1 : 0;
    }

    


}