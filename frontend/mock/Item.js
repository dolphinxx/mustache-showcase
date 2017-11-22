const res_base = '/res';
module.exports = function (values) {
    for (let o in values) {
        if (values.hasOwnProperty(o)) {
            Object.defineProperty(this, o, {
                writable: true,
                value: values[o],
                configurable: false,
                enumerable: values[o] instanceof Array || values[o] instanceof Object
            });
        }
    }
    if(values.hasOwnProperty('image')) {
        Object.defineProperty(this, 'imageUrl', {get: () => this.image.startsWith('http') ? this.image : res_base + this.image});
    }
    this.link = this.parent ? `/${this.parent.link}/${this.id}` : `/${this.id}`;
};