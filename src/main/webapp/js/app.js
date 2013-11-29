
// ---------------------------------------------------------------
// Namespace

var codate = {};
codate.router = {};
codate.view = {};
codate.layout = {};
codate.viewmodel = {};
codate.datasource = {};
codate.model = {};


//---------------------------------------------------------------
// Model

codate.model.Produtor = kendo.data.Model.define({
    id : "id",
    fields : {
        id : {
            editable : false,
            nullable : true
        },
        tipoPessoa : {
            type : "string",
            validation : {
                required : false
            },
            defaultValue : "JURIDICA"
        },
        cpfCnpj : {
            type : "string",
            validation : {
                required : false
            },
            defaultValue : null
        },
        nomeRazao : {
            type : "string",
            validation : {
                required : false
            },
            defaultValue : null
        },
        inscricaoEstadual : {
            type : "string",
            validation : {
                required : false
            },
            defaultValue : null
        },
        logradouro : {
            type : "string",
            validation : {
                required : false
            },
            defaultValue : null
        },
        numero : {
            type : "string",
            validation : {
                required : false
            },
            defaultValue : null
        },
        complemento : {
            type : "string",
            validation : {
                required : false
            },
            defaultValue : null
        },
        municipio : {
            type : "string",
            validation : {
                required : false
            },
            defaultValue : null
        },
        estado : {
            validation : {
                required : true
            },
            defaultValue : "MS"
        },
        cep : {
            type : "string",
            validation : {
                required : false
            },
            defaultValue : null
        },
        telefone : {
            type : "string",
            validation : {
                required : false
            },
            defaultValue : null
        },
        email : {
            type : "string",
            validation : {
                required : false
            },
            defaultValue : null
        }
    }
});


codate.model.Fazenda = kendo.data.Model.define({
    id : "id",
    fields : {
        id : {
            type : "number",
            defaultValue : null,
            editable : false
        },
        nome : {
            type : "string",
            defaultValue : null,
            validation : {
                required : true
            }
        },
        area : {
            type : "number",
            defaultValue : 0.0,
            validation : {
                required : false
            }
        },
        matricula : {
            type : "string",
            defaultValue : null,
            validation : {
                required : true
            }
        },
        municipio : {
            type : "string",
            defaultValue : null,
            validation : {
                required : true
            }
        },
        estado : {
            type : "string",
            defaultValue : "MS",
            validation : {
                required : true
            }
        },
        produtor: {
            defaultValue : null
        }
    }
});



//---------------------------------------------------------------
//Data Source

codate.datasource.Consulta = new kendo.data.DataSource({
    transport : {
        read : {
            type : "GET",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : "/cadrural/servicos/produtor/produtor"
        },
        remove : {
            type : "DELETE",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : function(model){
                return "/cadrural/servicos/produtor/produtor?idProdutor=" + model.id;
            }
        }
    },
    schema : {
        model : codate.model.Produtor
    }
});


codate.datasource.Produtor = new kendo.data.DataSource({
    transport : {
        create : {
            type : "POST",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : "/cadrural/servicos/produtor/produtor"
        },
        update : {
            type : "PUT",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : "/cadrural/servicos/produtor/produtor"
        },
        parameterMap : function(model, operation){
            if(model && (operation === "create" || operation === "update")){
                return kendo.stringify(model);
            }
            return model;
        }
	},
	schema : {
	    model : codate.model.Produtor
	}
});

codate.datasource.Fazenda = new kendo.data.DataSource({
    transport : {
        read : {
            type : "GET",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : function(model){
                return "/cadrural/servicos/produtor/fazenda?idProdutor=" + model.produtor.id;
            }
        },
        create : {
            type : "POST",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : "/cadrural/servicos/produtor/fazenda"
        },
        update : {
            type : "PUT",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : "/cadrural/servicos/produtor/fazenda"
        },
        remove : {
            type : "DELETE",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : function(model){
                return "/cadrural/servicos/produtor/fazenda?idFazenda=" + model.id;
            }
        },
        parameterMap : function(model, operation){
            if(model && (operation === "create" || operation === "update")){
                return kendo.stringify(model);
            }
            return model;
        }
    },
    schema : {
        model : codate.model.Fazenda
    }
});


codate.datasource.Estado = new kendo.data.DataSource({
    data : [{value: "AC", text: "AC"},
            {value: "AL", text: "AL"},
            {value: "AP", text: "AP"},
            {value: "AM", text: "AM"},
            {value: "BA", text: "BA"},
            {value: "CE", text: "CE"},
            {value: "DF", text: "DF"},
            {value: "ES", text: "ES"},
            {value: "GO", text: "GO"},
            {value: "MA", text: "MA"},
            {value: "MT", text: "MT"},
            {value: "MS", text: "MS"},
            {value: "MG", text: "MG"},
            {value: "PA", text: "PA"},
            {value: "PB", text: "PB"},
            {value: "PR", text: "PR"},
            {value: "PE", text: "PE"},
            {value: "PI", text: "PI"},
            {value: "RR", text: "RR"},
            {value: "RO", text: "RO"},
            {value: "RJ", text: "RJ"},
            {value: "RN", text: "RN"},
            {value: "RS", text: "RS"},
            {value: "SC", text: "SC"},
            {value: "SP", text: "SP"},
            {value: "SE", text: "SE"},
            {value: "TO", text: "TO"}]
});

codate.datasource.TipoPessoa = new kendo.data.DataSource({
    data : [{value : "FISICA", text : "Física"},
            {value : "JURIDICA", text : "Jurídica"}]
});


//---------------------------------------------------------------
// View Model

codate.viewmodel.Consulta = kendo.observable({
    listaProdutor : codate.datasource.Consulta,

    abrir : function(){
        codate.datasource.Consulta.read();
    },

    excluir : function(evt){
        codate.datasource.Consulta.remove(evt.data);
        codate.datasource.Consulta.sync();
    },

    editar : function(evt){
        var produtorsel = evt.data;
        //var produtorsel = evt.sender.dataItem(evt.sender.select());
        codate.viewmodel.Produtor.passarParametro(produtorsel);
        codate.router.App.navigate("/produtor");
    }
});

codate.viewmodel.Produtor = kendo.observable({
    model : null,
    listaTipoPessoa : codate.datasource.TipoPessoa,
    listaEstado : codate.datasource.Estado,
    parametros : null,

    passarParametro : function(param){
        this.set("parametros", param);
    },

    abrir : function(){
        // Se tiver parametro é pq foi passado antes de abrir
        if(this.get("parametros")){
            this.set("model", this.get("parametros"));
            this.set("parametros", null);
        }
        else{
            this.set("model", new codate.model.Produtor());
        }
    },

    confirmar : function(){
        var validator = $(".codate-form").kendoValidator().data("kendoValidator");
        if (validator.validate()) {
            var varmodel = this.get("model");
            codate.datasource.Produtor.insert(0,varmodel);
            codate.datasource.Produtor.sync();
            codate.router.App.navigate("/consulta");
        }
    },

    cancelar : function(){
        codate.router.App.navigate("/consulta");
    }

});

codate.viewmodel.Fazenda = kendo.observable({

    model : new codate.model.Fazenda(),
    listaEstado : codate.datasource.Estado,
    listaProdutor : codate.datasource.Consulta,

    confirmar : function(){
        var varmodel = this.get("model");
        codate.datasource.Fazenda.insert(0,varmodel);
        codate.datasource.Fazenda.sync();
    },

    cancelar : function(){
    }

});

//---------------------------------------------------------------
// Layout e View

codate.layout.Template = new kendo.Layout("codate-template");
codate.view.Consulta = new kendo.View("codate-consulta", { model : codate.viewmodel.Consulta});
codate.view.Produtor = new kendo.View("codate-produtor", { model : codate.viewmodel.Produtor});
codate.view.Fazenda = new kendo.View("codate-fazenda", { model : codate.viewmodel.Fazenda});



// ---------------------------------------------------------------
// Routers

codate.router.App = new kendo.Router({
    init : function(){
        codate.layout.Template.render("#codate-container");
    }
});

codate.router.App.route("/consulta", function(){
    codate.viewmodel.Consulta.abrir();
    codate.layout.Template.showIn("#conteudo", codate.view.Consulta);
});

codate.router.App.route("/produtor", function(){
    codate.viewmodel.Produtor.abrir();
    codate.layout.Template.showIn("#conteudo", codate.view.Produtor);
});

codate.router.App.route("/fazenda", function(){
    codate.layout.Template.showIn("#conteudo", codate.view.Fazenda);
});



$(function(){
    codate.router.App.start();
});