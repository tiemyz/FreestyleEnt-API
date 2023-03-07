# FreestyleEnt

# FreestyleEnt
## Endpoints

- [Cadastro de despesa com artistas](#cadastro-de-despesa-com-artistas)
- [Detalhar despesa](#detalhar-despesa)
- Listar despesas
- Apagar despesa
- Editar despesa

---

### Cadastro de despesa com artistas

`POST` FreestyleEnt/api/v1/despesa_artista

**Exemplo de entrada**

```js
{
    "artista": 'new order',
    "gravadora": 'factory',
    "valor": 100.000,
    "conta_id": 1,
    "data": 2023-01-01,
    "dia_apresentacao": 2023-05-05,
    "detalhes": ''
}
```

**Campos da Requisição**
| Campo | Obrigatório | Tipo  | Descrição |
|-------|:-------------:|-------|-----------|
|artista |sim|texto|Nome do artista ou banda, com no máximo 50 caractesres
|gravadora|sim|texto|Nome da gravadora responsável pela gestão do artista ou banda, com no máximo 50 caracteres
|valor|sim|decimal| Valor da despesa, deverá ser maior que zero
|conta_id|sim|int| O id da conta de despesa, não precisa ser previamente cadastrada
|data|sim|data| A data em que a despesa foi cadastrada
|dia_apresentacao|sim|data| A data em que o artista ou banda irá se apresentar
|detalhes|não|texto| Uma descrição de detalhes com no máximo 300 caracteres

**Códigos da Resposta**
|códigos|descrição
|-|-
201 | a despesa foi cadastrada com sucesso
400 | os dados enviados são inválidos

---

###Detalhar Despesa

`GET` FreestyleEnt/api/v1/despesa_artista/{id}

**Exemplo de resposta**

```js
{
    "conta": {
        "id": 1,
        "gravadora": 'factory',
        "valor": 100.000
    }
    "data": 2023-01-01,
    "artista": 'new order',
    "dia_apresentacao": 2023-05-05,
    "detalhes": ''
}
```

**Códigos da Resposta**

|código|descrição
|-|-
200 | os dados da despesa foram retornados
404 | não existe despesa como ID informado