/**
 * Modelos da aplicação.
 *
 * @author    David Buzatto
 * @copyright (c) 2012, by David Buzatto
 * @date      05 de Julho de 2012
 *
 */

Ext.ns( "Modelos" );

/**
 * Modelo para os tipos de consulta.
 */
Ext.define( "Modelos.TipoConsulta", {
    extend: "Ext.data.Model",
    fields: [
        { name: "campo",  type: "string" },
        { name: "tipo",  type: "string" }
    ]
});

/**
 * Modelos das classes Java definidas no backend.
 * 
 * Usa-se a função Uteis.createModel criada para poder associar de forma mais
 * simples um modelo em outro.
 */
Uteis.createModel({ 
    name: "Modelos.Instituicao",
    fields: [
        { name: "id",  type: "int" },
        { name: "nome",  type: "string" }
    ]
});


Uteis.createModel({ 
    name: "Modelos.Pais",
    fields: [
        { name: "id",  type: "int" },
        { name: "nome",  type: "string" },
        { name: "sigla",  type: "string" }
    ]
});


Uteis.createModel({ 
    name: "Modelos.Estado",
    fields: [
        { name: "id",  type: "int" },
        { name: "nome",  type: "string" },
        { name: "sigla",  type: "string" },
        { name: "pais", type: "Modelos.Pais" }
    ]
});


Uteis.createModel({ 
    name: "Modelos.Cidade",
    fields: [
        { name: "id",  type: "int" },
        { name: "nome",  type: "string" },
        { name: "estado", type: "Modelos.Estado" }
    ]
});


Uteis.createModel({ 
    name: "Modelos.Campus",
    fields: [
        { name: "id",  type: "int" },
        { name: "nome",  type: "string" },
        { name: "instituicao", type: "Modelos.Instituicao" },
        { name: "cidade", type: "Modelos.Cidade" }
    ]
});


Uteis.createModel({ 
    name: "Modelos.Area",
    fields: [
        { name: "id",  type: "int" },
        { name: "nome",  type: "string" },
        { name: "campus", type: "Modelos.Campus" }
    ]
});


Uteis.createModel({ 
    name: "Modelos.Curso",
    fields: [
        { name: "id",  type: "int" },
        { name: "nome",  type: "string" },
        { name: "area", type: "Modelos.Area" }
    ]
});


Uteis.createModel({ 
    name: "Modelos.Professor",
    fields: [
        { name: "id",  type: "int" },
        { name: "nome",  type: "string" },
        { name: "sobrenome",  type: "string" },
        { name: "telefoneResidencial",  type: "string" },
        { name: "telefoneCelular",  type: "string" }
    ]
});


Uteis.createModel({ 
    name: "Modelos.Usuario",
    fields: [
        { name: "id",  type: "int" },
        { name: "email",  type: "string" },
        { name: "senha",  type: "string" },
        { name: "nome",  type: "string" },
        { name: "sobrenome",  type: "string" },
        { name: "ativo",  type: "boolean" },
        { name: "tipo",  type: "string" },
        { name: "curso", type: "Modelos.Curso" },
        { name: "area", type: "Modelos.Area" },
        { name: "campus", type: "Modelos.Campus" },
        { name: "professor", type: "Modelos.Professor" }
    ]
});
