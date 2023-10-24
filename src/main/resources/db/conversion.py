import mysql.connector
from sqlalchemy import create_engine
import pandas as pd
import sqlalchemy


df = pd.read_csv("list_1.csv", sep=',', quotechar='\"',encoding='utf8')

conn = create_engine("mysql+pymysql://root:password@localhost:3306/app_db?charset=utf8mb4")

txt_cols = df.select_dtypes(include = ['object']).columns

df.to_sql("dictionary", conn, if_exists='replace', index=False, dtype = {col_name: sqlalchemy.types.NVARCHAR(length=255) for col_name in txt_cols})