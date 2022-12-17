drop table CONFIGURATION_ARTICLES;

create table CONFIGURATION_ARTICLES
(
    CONFIGURATION_ID INTEGER not null,
    ARTICLES_ID      INTEGER not null,
    constraint FKHJ1NE465JO22TBH20Q64UU3JP
        foreign key (ARTICLES_ID) references ARTICLE on delete CASCADE,
    constraint FKHRJ05S8T4OBDN0AHUKUBK4GHH
        foreign key (CONFIGURATION_ID) references CONFIGURATION on delete CASCADE
);

