package controller;

public class Forward {
    private String url;
    private boolean redirect;

    public Forward(final String url, final boolean redirect) {
        this.url = url;
        this.redirect = redirect;
    }

    public Forward(final String url) {
        this(url, true);
    }

    public String getUrl() {
        return url;
    }

    public boolean isRedirect() {
        return redirect;
    }
}
