//
//  ColorPicker.m
//  TestDelegate
//
//  Created by iem on 11/12/2014.
//  Copyright (c) 2014 iem. All rights reserved.
//

#import "ColorPicker.h"

@interface ColorPicker ()

@end

@implementation ColorPicker

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Navigation

 //In a storyboard-based application, you will often want to do a little preparation before navigation

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
//     Get the new view controller using [segue destinationViewController].
//     Pass the selected object to the new view controller.
}

- (IBAction)changeToGreen:(id)sender {
    UIColor *green = [[UIColor alloc] initWithRed:(26.0/255)
                                            green:(197.0/255)
                                             blue:(115.0/255)
                                            alpha:1];
    [[self delegate] userDidChooseColor:green];
}

- (IBAction)changeToOrange:(id)sender {
    UIColor *orange = [[UIColor alloc] initWithRed:(238.0/255)
                                             green:(102.0/255)
                                              blue:(6.0/255)
                                             alpha:1];
    [[self delegate] userDidChooseColor:orange];
}

- (IBAction)changeToRed:(id)sender {
    UIColor *red = [[UIColor alloc] initWithRed:(110.0/255)
                                          green:(0.0/255)
                                           blue:(47.0/255)
                                          alpha:1];
    [[self delegate] userDidChooseColor:red];
}



@end
