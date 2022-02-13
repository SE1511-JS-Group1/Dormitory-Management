/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author lenovo_thinkpad
 */
public class ProjectFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public ProjectFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        Throwable t = new Throwable("");
        HttpServletRequest req = ((HttpServletRequest) request);
        HttpServletResponse res = ((HttpServletResponse) response);
        Account account = (Account) req.getSession().getAttribute("account");
        try {
            System.out.println(req.getServletPath());
            if (req.getServletPath().contains(".js") || req.getServletPath().contains(".css") || req.getServletPath().contains(".images") || req.getServletPath().equals("/logout")) {
                System.out.println("Common Page/Support");
                chain.doFilter(request, response);
            } else if (account == null) {
                System.out.println("Account null");
                String[] commonpages = {"/index.jsp", "/viewdom", "/home", "/login","/register", "/register_boarder.jsp", "/register_staff.jsp", "/forgotpassword","forgotpassword.jsp"};
                ArrayList<String> commonpage = new ArrayList<>();
                Collections.addAll(commonpage, commonpages);
                if (commonpage.contains(req.getServletPath())) {
                    System.out.println("Account null accept");
                    chain.doFilter(request, response);
                } else {
                    System.out.println("Account null denied");
                    sendProcessingError(t, response);
                }
            } else if (account.getRole() == 1) {
                if (req.getServletPath().isEmpty()) {
                    req.getRequestDispatcher("/admin/home").forward(req, res);
                } else if (req.getServletPath().contains("/admin")) {
                    chain.doFilter(request, response);
                } else {
                    sendProcessingError(t, response);
                }
            } else if (account.getRole() == 2) {
                if (req.getServletPath().isEmpty()) {
                    req.getRequestDispatcher("/staff/home").forward(req, res);
                } else if (req.getServletPath().contains("/staff")) {
                    chain.doFilter(request, response);
                } else {
                    sendProcessingError(t, response);
                }
            } else if (account.getRole() == 3) {
                if (req.getServletPath().isEmpty()) {
                    req.getRequestDispatcher("/boarder/home").forward(req, res);
                } else if (req.getServletPath().contains("/boarder")) {
                    chain.doFilter(request, response);
                } else {
                    sendProcessingError(t, response);
                }
            }
        } catch (ServletException se) {
//            log(se.getMessage());
            sendProcessingError(t, response);
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("ProjectFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("ProjectFilter()");
        }
        StringBuffer sb = new StringBuffer("ProjectFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
               /* pw.print("<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "    <head>\n"
                        + "        <title>Dormitory Management System</title>\n"
                        + "        <meta charset=\"UTF-8\">\n"
                        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n"
                        + "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\n"
                        + "        <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js\" integrity=\"sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB\" crossorigin=\"anonymous\"></script>\n"
                        + "        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js\" integrity=\"sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13\" crossorigin=\"anonymous\"></script>\n"
                        + "        <link rel='icon' href='https://by.com.vn/xQTXSg'>   \n"
                        + "        <link href=\"css/overview.css\" rel=\"stylesheet\">\n"
                        + "    </head>\n"
                        + "    <body>\n"
                        + "        <section class=\"h-100 gradient-form\" style=\"background-color: #eee;\">\n"
                        + "            <div class=\"container py-5 h-90\">\n"
                        + "                <div class=\"row d-flex justify-content-center align-items-center h-90\">\n"
                        + "                    <div class=\"col-xl-8\">\n"
                        + "                        <div class=\"card rounded-3 text-black\">\n"
                        + "                            <img style=\"z-index: 3;\" src=\"https://goeco.link/GtBgy\">\n"
                        + "                            <button style=\"z-index: 9; position: fixed;margin-left: 25%; margin-top: 30%;\" class=\"btn btn-outline-info\" onclick=\"history.back();\"> << Back </button>\n"
                        + "                        </div>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "            </div>\n"
                        + "        </section>\n"
                        + "    </body>\n"
                        + "</html>\n"
                        + ""); //NOI18N
*/
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
