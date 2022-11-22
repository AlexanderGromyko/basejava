package com.urise.webapp;

import com.urise.webapp.model.*;
import com.urise.webapp.util.RandomGenerator;

import java.time.LocalDate;
import java.util.ArrayList;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume testResume = createResume();
        printResume(testResume);
    }

    private static void printResume(Resume resume) {
        System.out.println(resume);
        System.out.println("");
        System.out.println("Контакты:");
        resume.getContacts().forEach( (key, value) -> System.out.println(key.getTitle() + ": " + value));
        printSection(resume, SectionType.OBJECTIVE);
        printSection(resume, SectionType.PERSONAL);
        printSection(resume, SectionType.ACHIEVEMENTS);
        printSection(resume, SectionType.EXPERIENCE);
        printSection(resume, SectionType.EDUCATION);
    }

    private static void printSection(Resume resume, SectionType sectionType) {
        System.out.println("");
        System.out.println(sectionType.getTitle() + ":");
        System.out.println(resume.getSection(sectionType));
    }

    private static Resume createResume() {
        Resume resume = new Resume("Григорий Кислин");

        //EXPERIENCE (jobs)
        OrganizationSection jobOrganizations = new OrganizationSection(new ArrayList<>());

        Organization organization = createOrganization("Java Online Projects", "http://javaops.ru");
        organization.addPeriod(createPeriod(LocalDate.of(2013,10,1),
                LocalDate.now(),
                "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок."));
        jobOrganizations.addOrganization(organization);

        organization = createOrganization("Wrike", "https://www.wrike.com");
        organization.addPeriod(createPeriod(LocalDate.of(2014,10,1),
                LocalDate.of(2016,1,1),
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        jobOrganizations.addOrganization(organization);

        organization = createOrganization("RIT Center", "");
        organization.addPeriod(createPeriod(LocalDate.of(2012,4,1),
                LocalDate.of(2014,10,1),
                "Java архитектор",
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), " +
                        "миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части " +
                        "системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). " +
                        "Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, " +
                        "Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"));
        jobOrganizations.addOrganization(organization);

        organization = createOrganization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru");
        organization.addPeriod(createPeriod(LocalDate.of(2010,12,1),
                LocalDate.of(2012,4,1),
                "Ведущий программист",
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). " +
                        "Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа " +
                        "результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."));
        jobOrganizations.addOrganization(organization);

        organization = createOrganization("Yota", "https://www.yota.ru");
        organization.addPeriod(createPeriod(LocalDate.of(2008,6,1),
                LocalDate.of(2010,12,1),
                "Ведущий специалист",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                        "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                        "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"));
        jobOrganizations.addOrganization(organization);

        organization = createOrganization("Enkata", "http://enkata.com");
        organization.addPeriod(createPeriod(LocalDate.of(2007,3,1),
                LocalDate.of(2008,6,1),
                "Разработчик ПО",
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."));
        jobOrganizations.addOrganization(organization);

        organization = createOrganization("Siemens AG", "https://www.siemens.com/ru/ru/home.html");
        organization.addPeriod(createPeriod(LocalDate.of(2005, 1, 1),
                LocalDate.of(2007, 2, 1),
                "Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."));
        jobOrganizations.addOrganization(organization);

        organization = createOrganization("Alcatel", "http://www.alcatel.ru");
        organization.addPeriod(createPeriod(LocalDate.of(1997,9,1),
                LocalDate.of(2005,1,1),
                "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));
        jobOrganizations.addOrganization(organization);

        resume.setSection(SectionType.EXPERIENCE, jobOrganizations);

        //EDUCATION
        OrganizationSection educationOrganizations = new OrganizationSection(new ArrayList<>());

        organization = createOrganization("Coursera", "https://www.coursera.org/course/progfun");
        organization.addPeriod(createPeriod(LocalDate.of(2013,3,1),
                LocalDate.of(2013,5,1),
                "'Functional Programming Principles in Scala' by Martin Odersky", ""));
        educationOrganizations.addOrganization(organization);

        organization = createOrganization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366");
        organization.addPeriod(createPeriod(LocalDate.of(2011,3,1),
                LocalDate.of(2011,4,1),
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'", ""));
        educationOrganizations.addOrganization(organization);

        organization = createOrganization("Siemens AG", "http://www.siemens.ru");
        organization.addPeriod(createPeriod(LocalDate.of(2005,1,1),
                LocalDate.of(2005,4,1),
                "3 месяца обучения мобильным IN сетям (Берлин)", ""));
        educationOrganizations.addOrganization(organization);

        organization = createOrganization("Alcatel", "http://www.alcatel.ru");
        organization.addPeriod(createPeriod(LocalDate.of(1997,9,1),
                LocalDate.of(1998,3,1),
                "6 месяцев обучения цифровым телефонным сетям (Москва)", ""));
        educationOrganizations.addOrganization(organization);

        organization = createOrganization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru");
        organization.addPeriod(createPeriod(LocalDate.of(1987,9,1),
                LocalDate.of(1993,7,1),
                "Инженер (программист Fortran, C)", ""));
        organization.addPeriod(createPeriod(LocalDate.of(1993,9,1),
                LocalDate.of(1996,7,1),
                "Аспирантура (программист С, С++)", ""));
        educationOrganizations.addOrganization(organization);

        organization = createOrganization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru");
        organization.addPeriod(createPeriod(LocalDate.of(1984,9,1),
                LocalDate.of(1987,6,1),
                "Закончил с отличием", ""));
        educationOrganizations.addOrganization(organization);

        resume.setSection(SectionType.EDUCATION, educationOrganizations);

        //QUALIFICATIONS
        ListSection qualifications = new ListSection(new ArrayList<>());
        qualifications.addString("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.addString("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.addString("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.addString("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualifications.addString("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualifications.addString("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), " +
                "JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualifications.addString("Python: Django.");
        qualifications.addString("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.addString("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.addString("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, " +
                "JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualifications.addString("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        qualifications.addString("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer");
        qualifications.addString("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualifications.addString("Родной русский, английский \"upper intermediate\"");

        resume.setSection(SectionType.QUALIFICATIONS, qualifications);

        //ACHIEVEMENTS
        ListSection achievements = new ListSection(new ArrayList<>());
        achievements.addString("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система " +
                "мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        achievements.addString("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). " +
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");
        achievements.addString("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievements.addString("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на " +
                "стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievements.addString("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievements.addString("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievements.addString("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        resume.setSection(SectionType.ACHIEVEMENTS, achievements);

        //PERSONAL
        resume.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        //OBJECTIVE
        resume.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        //Contacts
        resume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.setContact(ContactType.SKYPE, "skype:grigory.kislin");
        resume.setContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.setContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.setContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.setContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.setContact(ContactType.HOMEPAGE, "http://gkislin.ru/");
        return resume;
    }

    private static Organization createOrganization(String name, String website) {
        Organization organization = new Organization(name, website);
        return organization;
    }

    private static Period createPeriod(LocalDate dateFrom, LocalDate dateTo, String title, String description) {
        Period period = new Period(dateFrom, dateTo, title, description);
        return period;
    }

    public static Resume resumeGenerator(String uuid, String name) {
        Resume resume = new Resume(uuid, name);
        resume.setContact(ContactType.PHONE, RandomGenerator.phoneNumber());
        resume.setContact(ContactType.SKYPE, RandomGenerator.skype(name));
        resume.setContact(ContactType.EMAIL, RandomGenerator.email(name));
        resume.setContact(ContactType.LINKEDIN, RandomGenerator.linkedin(name));
        resume.setContact(ContactType.GITHUB, RandomGenerator.github(name));
        resume.setContact(ContactType.STACKOVERFLOW, RandomGenerator.stackoverflow(name));
        resume.setContact(ContactType.HOMEPAGE, RandomGenerator.homepage(name));

        resume.setSection(SectionType.PERSONAL, new TextSection(RandomGenerator.randomExperienceTitle()));
        resume.setSection(SectionType.OBJECTIVE, new TextSection(RandomGenerator.randomExperienceTitle()));
        resume.setSection(SectionType.ACHIEVEMENTS, getFilledExperience());
        resume.setSection(SectionType.QUALIFICATIONS, getFilledExperience());
        resume.setSection(SectionType.EDUCATION, getFilledOrganizations());
        resume.setSection(SectionType.EXPERIENCE, getFilledOrganizations());
        return resume;
    }

    private static ListSection getFilledExperience() {
        final int MINIMUM = 7;
        final int MAXIMUM = 14;
        ListSection achievements = new ListSection(new ArrayList<>());
        for (int i = 0; i < RandomGenerator.randomNumber(MINIMUM, MAXIMUM); i++) achievements.addString(RandomGenerator.randomExperienceTitle());
        return achievements;
    }

    private static OrganizationSection getFilledOrganizations() {
        final int MINIMUM = 5;
        final int MAXIMUM = 10;
        OrganizationSection OrganizationsSection = new OrganizationSection(new ArrayList<>());
        for (int i = 0; i < RandomGenerator.randomNumber(MINIMUM, MAXIMUM); i++) {
            LocalDate minimumDate = RandomGenerator.randomLocalDate();
            Organization organization = createOrganization(RandomGenerator.randomCompanyName(), RandomGenerator.site());
            organization.addPeriod(createPeriod(minimumDate,
                    RandomGenerator.randomLocalDate(minimumDate),
                    RandomGenerator.randomExperienceTitle(),
                    RandomGenerator.randomExperienceDescription()));
            OrganizationsSection.addOrganization(organization);
            }
        return OrganizationsSection;
    }
}
