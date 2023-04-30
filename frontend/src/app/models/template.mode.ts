
export interface Action {
    id: Number,
    name: String
}
export interface Template {
    id: Number,
    name: String,
    actions: Action[]
}
