var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

axios.defaults.headers.common['X-CSRF-TOKEN'] = token;

class Errors{
    constructor (){
        this.errors = {};
    }
    record(listErrors){
        this.errors = listErrors;
    }
    get(field){
        if(this.errors[0]){
            var data = $.grep(this.errors,function(obj){
                return obj.field === field;
            });
            if(data.length!==0){
                return data[0].defaultMessage
            }
        }
    }
    has(field){
        if(this.errors[0]){
            var data = $.grep(this.errors,function(obj){
                return obj.field === field;
            });

            return (data.length !== 0)

        }
    }
}

new Vue({
    el: "#app",
    data: {
        name: "",
        phoneNumber: "",
        username: username,
        listErrors: new Errors()
    },
    methods: {
        onSubmit(){
            axios.post("/api/recomendaciones/"+this.username+"/crear",this.$data)
                .then(response => {
                    console.log(response);
                    showMessageTimer("Agregado","Recomendacion Agregada","success",1000);
                    this.clearData();

                })
                .catch(error => {
                    console.log(error);
                })
        },
        clearData(){
            this.name = "";
            this.phoneNumber =  "";
        }
    }
})