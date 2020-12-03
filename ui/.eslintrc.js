module.exports = {
    extends: [
        'plugin:vue/recommended',
    ],
    rules: {
        'vue/max-attributes-per-line': 'off',
    },
    parserOptions: {
        parser: 'babel-eslint',
        sourceType: 'module',
    },
    settings: {
        'import/resolver': {
            node: {
                extensions: ['.js', '.vue'],
            },
        },
    },
}
