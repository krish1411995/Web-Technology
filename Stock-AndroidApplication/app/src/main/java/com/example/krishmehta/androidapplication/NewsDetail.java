package com.example.krishmehta.androidapplication;

/**
 * Created by krishmehta on 11/17/17.
 */

public class NewsDetail {
    private String newstitle,newsauthor,newspublish,newsurl;
    public NewsDetail(String newstitle, String newsauthor,String newspublish,String newsurl) {
        this.newstitle=newstitle;
        this.newsauthor=newsauthor;
        this.newspublish=newspublish;
        this.newsurl=newsurl;
    }

    public NewsDetail()
    {

    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewspublish() {
        return newspublish;
    }

    public void setNewspublish(String newspublish) {
        this.newspublish = newspublish;
    }
    public String getNewsauthor() {
        return newsauthor;
    }

    public void setNewsauthor(String newsauthor) {
        this.newsauthor = newsauthor;
    }
    public String getNewsurl() {
        return newsurl;
    }

    public void setNewurl(String newsurl) {
        this.newsurl = newsurl;
    }
}
