CREATE TABLE ${DbConst.T_USERS}(
            ${DbConst.U_ID}        INTEGER PRIMARY KEY AUTOINCREMENT,
            ${DbConst.U_EMAIL}     TEXT UNIQUE NOT NULL,
            ${DbConst.U_PASS}      TEXT NOT NULL,
            ${DbConst.U_FIRSTNAME} TEXT NOT NULL,
            ${DbConst.U_LASTNAME}  TEXT NOT NULL,
            ${DbConst.U_GROUP_NO}  INTEGER NOT NULL
        );

INSERT INTO ${DbConst.T_USERS}
            (${DbConst.U_EMAIL}, ${DbConst.U_PASS},
             ${DbConst.U_FIRSTNAME}, ${DbConst.U_LASTNAME}, ${DbConst.U_GROUP_NO})
        VALUES
            ('ivanov@bsu.by','1111','Иван','Иванов',1),
            ('petrova@bsu.by','2222','Анна','Петрова',2),
            ('smith@bsu.by',  '3333','John','Smith',3),
            ('lee@bsu.by',    '4444','Min','Lee',4);

CREATE TABLE ${DbConst.T_SCHEDULE}(
                ${DbConst.S_ID}       INTEGER PRIMARY KEY AUTOINCREMENT,
                ${DbConst.S_DAY}      TEXT NOT NULL,
                ${DbConst.S_GROUP_NO} INTEGER NOT NULL,
                ${DbConst.S_SUBJECT}  TEXT NOT NULL,
                ${DbConst.S_TIME}     TEXT NOT NULL,
                ${DbConst.S_ROOM}     TEXT NOT NULL
            );