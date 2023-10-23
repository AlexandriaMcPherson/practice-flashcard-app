import mysql.connector
# import pandas as pd


# df = pd.read_csv("ja_en_utf8.tsv", sep='\t')

conn = mysql.connector.connect(
    host="localhost",
    port= 3306,
    user="root",
    password="password",
    database="app_db"
)

cursor = conn.cursor()

# df.to_sql("dictionary", conn, if_exists='replace', index=False)

cursor.execute("CREATE TABLE IF NOT EXISTS dictionary (jp TEXT, en TEXT);")

with open("ja_en_utf8.tsv", "r") as tsv_file:
    for line in tsv_file:
        columns = line.strip().split("\t")
        cursor.execute("INSERT INTO dictionary (jp, en) VALUES (?, ?;", columns)

conn.commit()
conn.close()