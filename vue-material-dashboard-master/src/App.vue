<template>
    <router-view @authenticated="setAuthenticated" />
</template>

<script>
    export default {
        data() {
            return {
                authenticated: false,
            };
        },
        mounted() {
            if (!this.authenticated) {
                this.$router.replace({ path: "/login" });
            }
        },
        methods: {
            setAuthenticated(status) {
                this.authenticated = status;
            }
        }
    };
</script>
