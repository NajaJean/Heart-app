<template>
    <div id="login" class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33">
        <md-card>
            <md-card-header data-background-color="blue">
                <h2 class="Login" font-weight="bold">Log in</h2>
            </md-card-header>
            <md-card-content>
                <md-input type="text" name="username" v-model="input.username" placeholder="Username" />
                <md-input type="password" name="password" v-model="input.password" placeholder="Password" />
                <md-button type="button" v-on:click="login()">Login</md-button>
            </md-card-content>
        </md-card>
    </div>
</template>

<script>
    export default {
        name: 'LogIn',
        data() {
            return {
                input: {
                    username: "",
                    password: ""
                },
                authenticated: false
            }
        },
        mounted() {
            if (!this.authenticated) {
                this.$router.replace({ path: "/" });
            }
        },
        methods: {
            login() {
                if (this.input.username != "" && this.input.password != "") {
                    if (this.input.username == "admin" && this.input.password == "mathilde") {
                        this.authenticated = true;
                        this.$router.replace({ path: "/dashboard" });
                    } else {
                        console.log("The username and / or password is incorrect");
                    }
                } else {
                    console.log("A username and password must be present");
                }
            }
        }

        
    }
</script>

<style scoped>
    #login {
        width: 800px;
        border: 2px solid #CCCCCC;
        margin: auto;
        margin-top: 200px;
        padding: 20px;
        background-color: #FFFFFF;
    }
</style>