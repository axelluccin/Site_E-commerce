package com.lvd.repository;


import com.google.common.collect.ImmutableSet;
import com.lvd.domain.User;
import org.eclipse.jetty.server.Response;
import restx.factory.Component;
import restx.jongo.JongoCollection;
import javax.inject.Named;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by charlesvienne on 29/02/2016.
 */
@Component
public class UserRepo {
    private JongoCollection user;

    public UserPrincipal createSession(User user) {
        return new UserPrincipal() {
            @Override
            public int getId() {
                return user.getId();
            }

            @Override
            public String getFirstname() {
                return user.getFirstname();
            }

            @Override
            public String getLogin() {
                return user.getLogin();
            }

            @Override
            public String getMail() {
                return user.getMail();
            }

            @Override
            public ImmutableSet<String> getPrincipalRoles() {
                return null;
            }

            @Override
            public String getName() {
                return user.getName();
            }
        };
    }

    public User get(int id) {
        User profil = new User();
        User tmp = user.get().findOne("{id : #}", id).as(User.class);
        if (tmp != null) {
            profil = tmp;
        }
        else {
            profil.setId(0);
        }
        return profil;
    }
//TODO chercher comment parser un json
    public UserPrincipal authenticate(User obj) {
        User profile = user.get().findOne("{mail : #, password : #}", obj.getMail(),
                sha1(obj.getPassword())).as(User.class);
        return createSession(profile);
    }
    public User save(User profil) {
        Date dt = new Date();
        SimpleDateFormat sdt = new SimpleDateFormat("yyddmmss");
        String tmpPwd = sdt.format(dt) + "_temp_";
        String pwd = sha1(tmpPwd);
        String title  = "Sign in Lvd.com";
        String description = "Welcome in LVD shop\n\nYou can place in order Reunion Island products \n\n" +
                "For more information, you can join us \n\nYour tempory Password : " + tmpPwd + "\n\n"+
                "Think change you password and complete your account\n\n\n Cheers";

        MailSender msd = new MailSender(profil.getMail(), title, description);
        if(msd.send()) {
            profil.setId(lastOne().getId() + 1);
            profil.setPassword(pwd);
            profil.setMail(profil.getMail());
            user.get().insert(profil);
        }
        else {
            System.out.printf("An error occured to sended mail...");
        }
        return profil;
    }
    public User update(User profil) {
        if(profil.getPassword().length() != 40) {
            profil.setPassword(sha1(profil.getPassword()));
        }
        user.get().update("{id : #}", profil.getId()).with(profil);
        return profil;
    }
    public User lastOne() {
        Iterable<User> profils = user.get().find().sort("{ _id : -1 }").limit(1).as(User.class);
        Iterator<User> iter = profils.iterator();
        User profil = new User();
        while (iter.hasNext()) {
            profil = iter.next();
        }
        return profil;
    }
    private String sha1(String input) {
        MessageDigest mDigest = null;
        try {
            mDigest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (byte aResult : result) {
            sb.append(Integer.toString((aResult & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    public UserRepo(@Named("user") JongoCollection user) {
        this.user = user;
    }

}