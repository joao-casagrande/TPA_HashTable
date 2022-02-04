# Funcionalidades
**IMPORTANTE!** Os prints das funcionalidades estão na ordem de execução para facilitar a compreensão do processo.

## 1 - Carregar arquivo
![image](https://user-images.githubusercontent.com/42438976/152097130-7a2aaa64-c19c-4e3d-91b2-4be852d6eda8.png)
![image](https://user-images.githubusercontent.com/42438976/152097166-b13521ce-d68f-4855-bdde-54b400206985.png)
![image](https://user-images.githubusercontent.com/42438976/152097210-db11cfdd-cceb-423e-9289-db5f57ce668f.png)


## 3 - Inserir um contato
![image](https://user-images.githubusercontent.com/42438976/152097375-817a6485-e3a1-484f-8e18-674c5f76fb64.png)


## 2 - Localizar um contato
![image](https://user-images.githubusercontent.com/42438976/152097419-c7dabb99-8c2b-47cd-9a3d-5f29405ea661.png)


## 5 - Atualizar contato
![image](https://user-images.githubusercontent.com/42438976/152097534-ea00f553-a481-44bd-b698-6a57ed00fe40.png)


## 4 - Excluir um contato
![image](https://user-images.githubusercontent.com/42438976/152097571-1f24ceef-0340-49fc-a1e4-a11593e4a514.png)


## 6 - Salvar dados
![image](https://user-images.githubusercontent.com/42438976/152097588-a2333937-31c2-42ed-a6d6-18cc1e10296d.png)


## 0 - Sair
![image](https://user-images.githubusercontent.com/42438976/152097608-13df47b7-bacb-4e97-bf10-13144c6dbf31.png)

# Resultados

Abaixo, os resultados dos testes de performance ao carregar um arquivo de 1 milhão de registros em tamanhos de tabela variados:

| Tamanho Tabela | Tempo Carregamento(ms) | Tempo de Busca(ms) | Colisões |
|----------------|------------------------|--------------------|-----------|
| 53             | 1165                   | 7                  | 999947    |
| 97             | 821                    | 1                  | 999903    |
| 193            | 635                    | 1                  | 999807    |
| 389            | 647                    | 1                  | 999611    |
| 769            | 771                    | 1                  | 999231    |
| 1543           | 812                    | 0                  | 998457    |
| 3079           | 818                    | 0                  | 997857    |
| 6151           | 886                    | 1                  | 997857    |
| 12289          | 668                    | 1                  | 997857    |
| 24593          | 780                    | 0                  | 997857    |
| 49157          | 698                    | 1                  | 997857    |
| 98317          | 783                    | 0                  | 997857    |
| 196613         | 716                    | 0                  | 997857    |
| 393241         | 680                    | 1                  | 997857    |
| 786433         | 729                    | 0                  | 997857    |
| 1572869        | 629                    | 1                  | 997857    |
| 3145739        | 789                    | 1                  | 997857    |
| 6291469        | 840                    | 1                  | 997857    |
| 12582917       | 830                    | 0                  | 997857    |
| 25165843       | 719                    | 1                  | 997857    |
| 50331653       | 861                    | 1                  | 997857    |
| 100663319      | 1002                   | 1                  | 997857    |




