package com.example.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StaticFieldsAndRequests {
    private static final Logger log = Logger.getLogger(StaticFieldsAndRequests.class);
    public static User mainUser;
    public static List<Employee> employees = new ArrayList<>();
    static String ip = "http://localhost:8080";
    public static List<Application> applications = new ArrayList<>();
    public static int idButton;
    public static int idApplication;
    public static String logString;

    public static User getResponseUser(User mainUser) {
        HttpUriRequest request = new HttpGet(ip + "/users/byUsername/" + mainUser.getUsername());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса get класса User");
        } catch (IOException e) {
            log.error("нет ответа запроса get класса User");
            logString="Сервер недоступен";
        }
        request.setHeader("Accept", "application/json; charset=UTF-8");
        request.setHeader("Content-type", "application/json; charset=UTF-8");
        User checkUser = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = null;
                result = EntityUtils.toString(entity);
                log.info("получена информация объекта класса User");
                checkUser = User.parseUserFromJSON(result);
                if (checkUser == null) {
                    checkUser = new User();
                }
            }
        } catch (NullPointerException | IOException e) {
            log.error("не удалось получить информацию объекта класса User");
            //e.printStackTrace();
            logString="Сервер недоступен";
        }
        return checkUser;
    }

    public static List<Application> getResponseApplications(User mainUser) {
        HttpUriRequest request = new HttpGet(ip + "/applications/byClientId/" + mainUser.getId());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса get класса List<Application>");
        } catch (IOException e) {
            log.error("нет ответа запроса get класса List<Application>");
            e.printStackTrace();
        }
        request.setHeader("Accept", "application/json; charset=UTF-8");
        request.setHeader("Content-type", "application/json; charset=UTF-8");
        HttpEntity entity = response.getEntity();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ArrayList<Application> applications = new ArrayList<>();
        try {
            if (entity != null) {
                String result = null;
                result = EntityUtils.toString(entity);
                log.info("получена информация объекта класса List<Application>");
                Type applicationsListType = new TypeToken<ArrayList<Application>>() {
                }.getType();
                applications = gson.fromJson(result, applicationsListType);
                if (applications == null) {
                    applications = new ArrayList<>();
                }
            }
        } catch (IOException e) {
            log.error("не удалось получить информацию объекта класса List<Application>");
            e.printStackTrace();
        }
        return applications;
    }

    public static Employee getResponseEmployee(Application application) {
        HttpUriRequest request = new HttpGet(ip + "/employees/byId/" + application.getMasterId());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса get класса Employee");
        } catch (IOException e) {
            log.error("нет ответа запроса get класса Employee");
            e.printStackTrace();
        }
        request.setHeader("Accept", "application/json; charset=UTF-8");
        request.setHeader("Content-type", "application/json; charset=UTF-8");
        HttpEntity entity = response.getEntity();
        Employee employee = new Employee();
        try {
            if (entity != null) {
                String result = null;
                result = EntityUtils.toString(entity);
                log.info("получена информация объекта класса Employee");
                employee = employee.parseUserFromJSON(result);
                if (employee == null) {
                    employee = new Employee();
                }
            }
        } catch (IOException e) {
            log.error("не удалось получить информацию объекта класса Employee");
            e.printStackTrace();
        }
        return employee;
    }

    public static List<Employee> getResponseEmployees() {
        HttpUriRequest request = new HttpGet(ip + "/employees");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса get класса List<Employee>");
        } catch (IOException e) {
            log.error("нет ответа запроса get класса List<Employee>");
            e.printStackTrace();
        }
        request.setHeader("Accept", "application/json; charset=UTF-8");
        request.setHeader("Content-type", "application/json; charset=UTF-8");
        HttpEntity entity = response.getEntity();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            if (entity != null) {
                String result = null;
                result = EntityUtils.toString(entity);
                log.info("получена информация объекта класса List<Employee>");
                Type employeesListType = new TypeToken<ArrayList<Employee>>() {
                }.getType();
                employees = gson.fromJson(result, employeesListType);
                if (employees == null) {
                    employees = new ArrayList<>();
                }
            }
        } catch (IOException e) {
            log.error("не удалось получить информацию объекта класса List<Employee>");
            e.printStackTrace();
        }
        return employees;
    }

    public static List<Application> getResponseComponents() {
        HttpUriRequest request = new HttpGet(ip + "/components");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса get класса Component");
        } catch (IOException e) {
            log.error("нет ответа запроса get класса Component");
            e.printStackTrace();
        }
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        HttpEntity entity = response.getEntity();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ArrayList<Application> applications = new ArrayList<>();
        try {
            if (entity != null) {
                String result = null;
                result = EntityUtils.toString(entity);
                log.info("получена информация объектов класса Component");
                Type componentsListType = new TypeToken<ArrayList<Application>>() {
                }.getType();
                applications = gson.fromJson(result, componentsListType);
                if (applications == null) {
                    applications = new ArrayList<>();
                }
            }
        } catch (IOException e) {
            log.error("не удалось получить информацию объектов класса Components");
            e.printStackTrace();
        }
        return applications;
    }

    public static User postResponseUser(User mainUser) {
        HttpPost request = new HttpPost(ip + "/users");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        request.setEntity(new StringEntity(mainUser.getJson(), "UTF-8"));
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса post класса User");
        } catch (IOException e) {
            log.error("нет ответа запроса post класса User");
        }
        User checkUser = null;
        try {
        HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result;
                result = EntityUtils.toString(entity);
                log.info("получена информация объекта класса User");
                checkUser = User.parseUserFromJSON(result);
                if (checkUser == null) {
                    checkUser = new User();
                }
            }
        } catch (NullPointerException | IOException e) {
            log.error("не удалось получить информацию объекта класса User");
            //e.printStackTrace();
        }
        return checkUser; // возвращает объект юзер, если юзер с таким никнеймом уже существует
    }

//    public static void postResponseBill(Bill bill) {
//        HttpPost request = new HttpPost(ip + "/bills");
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        request.setEntity(new StringEntity(bill.getJson(), "UTF-8"));
//        request.setHeader("Accept", "application/json");
//        request.setHeader("Content-type", "application/json");
//        CloseableHttpResponse response = null;
//        try {
//            response = httpClient.execute(request);
//            log.info("получен ответ запроса post класса Bill");
//        } catch (IOException e) {
//            log.error("нет ответа запроса post класса Bill");
//            //e.printStackTrace();
//        }
//        try {
//            HttpEntity entity = response.getEntity();
//            Bill checkBill = new Bill();
//            if (entity != null) {
//                String result = null;
//                result = EntityUtils.toString(entity);
//                log.info("получена информация объекта класса Bill");
//            }
//        } catch (NullPointerException | IOException e) {
//            log.error("не удалось получить информацию объекта класса Bill");
//            //e.printStackTrace();
//        }
//    }

    public static void postResponseApplication(Application application) {
        HttpPost request = new HttpPost(ip + "/applications");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        request.setEntity(new StringEntity(application.getJson(), "UTF-8"));
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            log.info("получен ответ запроса post класса Application");
        } catch (IOException e) {
            log.error("нет ответа запроса post класса Application");
            //e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        try {
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                log.info("получена информация объекта класса Application");
            }
        } catch (IOException e) {
            log.error("не удалось получить информацию объекта класса Application");
            //e.printStackTrace();
        }
    }

//    public static void putResponseBill(Bill bill) {
//        HttpPut request = new HttpPut(ip + "/bills/byId/" + bill.getId());
//        request.setHeader("Accept", "application/json");
//        request.setHeader("Content-type", "application/json");
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        request.setEntity(new StringEntity(bill.getJson(), "UTF-8"));
//        CloseableHttpResponse response = null;
//        try {
//            response = httpClient.execute(request);
//            log.info("получен ответ запроса put класса Bill");
//        } catch (IOException e) {
//            log.error("нет ответа запроса put класса Bill");
//            //e.printStackTrace();
//        }
//        HttpEntity entity = response.getEntity();
//        try {
//            if (entity != null) {
//                String result = EntityUtils.toString(entity);
//                log.info("получена информация объекта класса Bill");
//            }
//        } catch (IOException e) {
//            log.error("не удалось получить информацию объекта класса Bill");
//            //e.printStackTrace();
//        }
//    }

    static void refresh() {
        applications = getResponseApplications(mainUser);
    }
}
