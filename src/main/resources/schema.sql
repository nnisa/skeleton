CREATE TABLE receipts (
  id INT UNSIGNED AUTO_INCREMENT,
  uploaded TIME DEFAULT CURRENT_TIME(),
  merchant VARCHAR(255),
  amount DECIMAL(12,2),
  receipt_type INT UNSIGNED,

  PRIMARY KEY (id)
);


CREATE TABLE tags (
  id INT UNSIGNED AUTO_INCREMENT,
  tagName VARCHAR(255),

  PRIMARY KEY (id)
);


CREATE TABLE receipts_tags (
  receiptId INT UNSIGNED,
  tagId INT UNSIGNED,

  CONSTRAINT receipt_tag_primaryKey PRIMARY KEY (receiptId, tagId),

  CONSTRAINT receipt_foreignKey FOREIGN KEY (receiptId) REFERENCES receipts (id),
  CONSTRAINT tag_foreignKey FOREIGN KEY (tagId) REFERENCES tags (id)
);

select * from receipts;
select * from tags;
