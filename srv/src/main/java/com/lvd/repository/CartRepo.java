package com.lvd.repository;

import com.lvd.domain.Article;
import com.lvd.domain.Cart;
import com.lvd.domain.User;
import restx.factory.Component;
import restx.jongo.JongoCollection;

import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by charlesvienne on 25/02/2016.
 */
@Component
public class CartRepo implements DatabaseRepo {
    private JongoCollection cart;
    private JongoCollection article;
    private JongoCollection user;

    public Iterable<Cart> getAll() {
        Iterable<Cart> cts = cart.get().find().as(Cart.class);
        return cts;
    }

    public Cart get(int idUser) {
        Cart ct = new Cart();
        Cart tmp = cart.get().findOne("{idUser : #, toValid : #}", idUser, 0).as(Cart.class);
        if(tmp == null) {
            ct.setIdUser(0);
            ct.setId(lastOne().getId() + 1);
        }
        else {
            ct = lastOne();
        }
        return ct;
    }

    public Iterable<Cart> getArchive(int idUser) {
        Iterable<Cart> ct = cart.get().find("{idUser : #, toValid : #}", idUser, 1).as(Cart.class);
        return ct;
    }

    public Cart lastOne() {
        Iterable<Cart> cts = cart.get().find().sort("{ _id : -1 }").limit(1).as(Cart.class);
        Iterator<Cart> iter = cts.iterator();
        Cart ct = new Cart();
        while (iter.hasNext()) {
            ct = iter.next();
        }
        return ct;
    }

    public Cart save(Object o) {
        Cart ct = (Cart) o ;
        ct.setToValid(0);
        cart.get().insert(ct);
        return ct;
    }

    public Cart update(Object o) {
       /* Desktop desktop = Desktop.getDesktop();
        String message = "mailto:charles.vienne@gmail.com?subject=First%20Email";
        URI uri = URI.create(message);
        desktop.mail(uri);*/
        Cart ct = (Cart) o ;
        cart.get().update("{id : #}", ct.getId()).with(ct);
        return ct;
    }

    public int delete(int id) {
        cart.get().remove("{id : #}", id);
        return 1;
    }

    public void validated(int idUser) {

       Cart ct = cart.get().findOne("{idUser : #, toValid : #}", idUser, 0).as(Cart.class);
        ct.setToValid(1);
        ct = update(ct);
        User usr = user.get().findOne("{id : #}", idUser).as(User.class);

        Date dt = new Date();
        SimpleDateFormat sdt = new SimpleDateFormat("dd MMMM yyyy 'to' hh:mm:ss");
        String title  = "Order in Lvd.com";
        int nbOrder = 11111 + (int)(Math.random() * (99999 - 11111));
        String description = "Thank you "+usr.getFirstname()+", to passed an order in LVD shop\n\nYour order number is " + nbOrder + "\n\n"+
                    "We will come back to you as soon as your order is done. \n\nYou could pick up your order\n\n" +
                "Adress : 5 Avenue de la République\n" +
                "         75001 Paris France \n\n you will pay directly to the shop.\n" +
                "We remind you that we accept only credit card and cash. \n\n" +
                "Your order : \n\n";
        int i;
        String[] articles = ct.getIdArticles().split("/");
        String[] atl;
        Article artl;
        int total = 0;
        for (i = 0; i < articles.length; ++i) {
            atl = articles[i].split(":");
            artl = article.get().findOne("{id : #}", Integer.parseInt(atl[1])).as(Article.class);
            description += "  - X" + atl[0]+" "+ artl.getName() + "(" + artl.getDescription() + ") --> "+
                    Integer.parseInt(atl[0]) * artl.getPrice() + " € \n";
            total += Integer.parseInt(atl[0]) * artl.getPrice();
        }

        description += "\nTotal  : "+ total +" € \n\n\nSee you soon,\nCheers";

        MailSender msd = new MailSender(usr.getMail(), title, description);

        if(!msd.send()) {
            System.out.printf("An error occured to sended mail...");
        }
    }

    public CartRepo(@Named("cart") JongoCollection cart, @Named("article") JongoCollection article, @Named("user") JongoCollection user ) {
        this.cart = cart;
        this.article = article;
        this.user = user;
    }
}
