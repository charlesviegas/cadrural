
// ---------------------------------------------------------------
// Namespace

var codate = {};
codate.router = {};
codate.view = {};
codate.layout = {};
codate.viewmodel = {};
codate.datasource = {};
codate.model = {};
codate.url = {};


//---------------------------------------------------------------
// URL: dos servicos JAVA-REST

codate.url.servicoProdutor = "/cadrural/servicos/produtor";
codate.url.servicoFazenda = "/cadrural/servicos/fazenda";


//---------------------------------------------------------------
//Model: Modelo das informacoes a serem trocadas entre os servicos e Aplicacao Front-End

codate.model.Produtor = kendo.data.Node.define({
    id : "id",
    
    hasChildren: "possuiFazenda",
    
    children: codate.datasource.Fazenda,
    
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


codate.model.Fazenda = kendo.data.Node.define({
    id : "id",
    hasChildren: false,
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
           defaultValue : { id : -1, nomeRazao : "Selecione"}
       }
    }
});


//---------------------------------------------------------------
// Data Source

codate.datasource.Produtor = new kendo.data.HierarchicalDataSource({
    
    requestStart : function(req){
        kendo.ui.progress($("#consultaGrid"), true);  
    },
    
    requestEnd : function(e){
        kendo.ui.progress($("#consultaGrid"), false);  
    },
    
    sync: function(req) {
        codate.router.App.navigate("/home");
    },
    
    error : function(req){
        alert(req.xhr.responseText);
    },
    
    transport : {
        read : {
            type : "GET",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : codate.url.servicoProdutor
        },
        create : {
            type : "POST",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : codate.url.servicoProdutor
        },
        update : {
            type : "PUT",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : codate.url.servicoProdutor
        },
        destroy : {
            type : "DELETE",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : function(model){
                return codate.url.servicoProdutor + "?idProdutor=" + model.id;
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
	    model : codate.model.Produtor
	}
});

codate.datasource.Fazenda = new kendo.data.HierarchicalDataSource({
    requestStart : function(req){
        kendo.ui.progress($("#consultaGrid"), true);  
    },
    
    requestEnd : function(e){
        kendo.ui.progress($("#consultaGrid"), false);  
    },
    
    sync: function(req) {
        codate.router.App.navigate("/home");
    },
    
    error : function(req){
        alert(req.xhr.responseText);
    },
    
    transport : {
        read : {
            type : "GET",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : function(model){
                return kendo.format(codate.url.servicoFazenda + "?idProdutor={0}", model.id);
            }
        },
        create : {
            type : "POST",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : codate.url.servicoFazenda
        },
        update : {
            type : "PUT",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : codate.url.servicoFazenda
        },
        destroy : {
            type : "DELETE",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            url : function(model){
                return codate.url.servicoFazenda + "?idFazenda=" + model.id;
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
    
    listaProdutor : codate.datasource.Produtor,
    
    desabilitaGrid : false,
    
    // Garante que ao abrir a tela o datasource leia as informacoes do servico
    abrir : function(){
        this.get("listaProdutor").read();
    },

    excluir : function(evt){
        //kendo.ui.progress($("#consultaGrid"), true);
        this.get("listaProdutor").remove(evt.data);
        this.get("listaProdutor").sync();
        //kendo.ui.progress($("#consultaGrid"), false);
    },

    editar : function(evt){
        var produtorsel = evt.data;
        codate.router.App.navigate("/produtor/" + produtorsel.id);
    }
});

codate.viewmodel.Produtor = kendo.observable({
    model : null,
    
    listaProdutor : codate.datasource.Produtor,
    listaTipoPessoa : codate.datasource.TipoPessoa,
    listaEstado : codate.datasource.Estado,

    // Carrega os dados do produtor existente ou cria um novo produtor para ser inserido
    abrir : function(id){
        var nmodel = (id) ? this.get("listaProdutor").get(id) : new codate.model.Produtor();
        this.set("model", nmodel);
    },

    confirmar : function(){
        var ds, index, model, 
            validator = $(".codate-form").kendoValidator().data("kendoValidator");
        
        if (validator.validate()) {
            
            ds = this.get("listaProdutor");
            model = this.get("model");
            index = ds.indexOf(model);

            // Eh uma alteracao
            if(index > -1){
                ds.insert(index, model);
            }
            else{
                ds.add(model);
            }
            ds.sync();
        }
    },

    cancelar : function(){
        this.get("model", null);
        codate.router.App.navigate("/home");
    }
    
});

codate.viewmodel.Fazenda = kendo.observable({

    model : null,
    
    listaProdutor : codate.datasource.Produtor,
    listaFazenda : codate.datasource.Fazenda,
    listaEstado : codate.datasource.Estado,

    // Carrega os dados da fazenda existente ou cria um novo produtor para ser inserido
    abrir : function(id){
        var nmodel = (id) ? this.get("listaFazenda").get(id) : new codate.model.Fazenda();
        this.set("model", nmodel);
    },
    
    confirmar : function(){
        var ds, index, model, 
        validator = $(".codate-form").kendoValidator().data("kendoValidator");
    
        if (validator.validate()) {
            
            ds = this.get("listaFazenda");
            model = this.get("model");
            index = ds.indexOf(model);
    
            // Eh uma alteracao
            if(index > -1){
                ds.insert(index, model);
            }
            else{
                ds.add(model);
            }
            ds.sync();
        }
    },

    cancelar : function(){
        this.get("model", null);
        codate.router.App.navigate("/home");
    }

});

//---------------------------------------------------------------
// Layout e View

codate.layout.Template = new kendo.Layout("codate-template", { model : codate.viewmodel.Consulta});
codate.view.Home = new kendo.View("codate-home");
codate.view.Produtor = new kendo.View("codate-produtor", { model : codate.viewmodel.Produtor});
codate.view.Fazenda = new kendo.View("codate-fazenda", { model : codate.viewmodel.Fazenda});



// ---------------------------------------------------------------
// Routers

codate.router.App = new kendo.Router({
    init : function(){
        codate.viewmodel.Consulta.abrir();
        codate.layout.Template.render("#codate-container");
    }
});

codate.router.App.route("/home", function(){
    codate.viewmodel.Consulta.abrir();
    codate.layout.Template.showIn("#conteudo", codate.view.Home);
});

codate.router.App.route("/produtor(/:id)", function(id){
    codate.viewmodel.Produtor.abrir(id);
    codate.layout.Template.showIn("#conteudo", codate.view.Produtor);
});

codate.router.App.route("/fazenda(/:id)", function(id){
    codate.viewmodel.Fazenda.abrir(id);
    codate.layout.Template.showIn("#conteudo", codate.view.Fazenda);
});



$(function(){
    codate.router.App.start();
});