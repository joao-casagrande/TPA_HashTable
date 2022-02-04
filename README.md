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

| Tamanho Tabela | Tempo Carregamento(ms) | Tempo Busca(ms) | Colisões |
|----------------|------------------------|-----------------|-----------|
| 53             | 1443                   | 1443            | 999947    |
| 97             | 929                    | 929             | 999903    |
| 193            | 1050                   | 1050            | 999807    |
| 389            | 1027                   | 1027            | 999611    |
| 769            | 1108                   | 1108            | 999231    |
| 1543           | 891                    | 891             | 998457    |
| 3079           | 909                    | 909             | 996921    |
| 6151           | 1855                   | 1855            | 993849    |
| 12289          | 1185                   | 1185            | 987711    |
| 24593          | 1464                   | 1464            | 975407    |
| 49157          | 1248                   | 1248            | 950843    |
| 98317          | 1311                   | 1311            | 901708    |
| 196613         | 1055                   | 1055            | 806494    |
| 393241         | 1058                   | 1058            | 655202    |
| 786433         | 1117                   | 1117            | 489853    |
| 1572869        | 1158                   | 1158            | 359120    |
| 3145739        | 1127                   | 1127            | 276554    |
| 6291469        | 1147                   | 1147            | 228942    |
| 12582917       | 1157                   | 1157            | 203800    |
| 25165843       | 1017                   | 1017            | 190680    |
| 50331653       | 1319                   | 1319            | 183836    |
| 100663319      | 1770                   | 1770            | 180741    |




